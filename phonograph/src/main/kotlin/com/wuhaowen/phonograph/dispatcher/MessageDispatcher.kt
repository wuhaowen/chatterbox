package com.wuhaowen.phonograph.dispatcher

import com.lmax.disruptor.IgnoreExceptionHandler
import com.lmax.disruptor.YieldingWaitStrategy
import com.lmax.disruptor.dsl.Disruptor
import com.lmax.disruptor.dsl.ProducerType
import com.wuhaowen.phonograph.dispatcher.uid.impl.CachedUidGenerator
import com.wuhaowen.phonograph.entity.Message
import com.wuhaowen.phonograph.proto.MessageOuterClass
import com.wuhaowen.phonograph.repository.UserTagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service

@Service
class MessageDispatcher(messageEventFactory: MessageEventFactory, threadFactory: MessageEventThreadFactory,
                        val translator: MessageEventTranslator, messageEventSaveHandler: MessageEventSaveHandler,
                        messageEventMQHandler: MessageEventMQHandler, disruptorConfig: DisruptorConfig) {

    @Autowired
    lateinit var uidGenerator: CachedUidGenerator
    @Autowired
    lateinit var userTagRepository: UserTagRepository


    private val disruptor = Disruptor<Message>(messageEventFactory, disruptorConfig.messageRingsize, threadFactory, ProducerType.MULTI, YieldingWaitStrategy())
    private val ringBuffer = disruptor.ringBuffer

    init {
        disruptor.handleExceptionsWith(IgnoreExceptionHandler())
        disruptor.handleEventsWith(messageEventSaveHandler)
                .then(messageEventMQHandler)
        disruptor.start()
    }

    fun dispatch(message: MessageOuterClass.Message) {
        if (message.tag == null) {
            ringBuffer?.publishEvent(translator, uidGenerator.uid, null, message)
        } else {
            val users = userTagRepository.findUsersByTag(tagName = message.tag)
            users.forEach {
                ringBuffer?.publishEvent(translator, uidGenerator.uid, it, message)
            }
        }

    }

    @Bean
    fun defaultUidGenerator(workerIdAssigner: DefaultWorkerIdAssigner): CachedUidGenerator = CachedUidGenerator().apply {
        setWorkerIdAssigner(workerIdAssigner)
        setBoostPower(1)
    }

    fun shutDown() {
        disruptor.shutdown()
    }

}

