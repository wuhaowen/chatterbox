package com.wuhaowen.voicetube.dispatcher

import com.lmax.disruptor.BlockingWaitStrategy
import com.lmax.disruptor.dsl.Disruptor
import com.lmax.disruptor.dsl.ProducerType
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.Executors

interface IMessageDispatcher{
    fun sendMessage(msg: String)
}

private const val RING_BUFFER_SIZE = 1024

@Service
class MessageDispatcher: IMessageDispatcher, DisposableBean, InitializingBean {


    @Autowired lateinit var messageEventFactory: MessageEventFactory
    lateinit var disruptor: Disruptor<MessageEvent>

    override fun sendMessage(msg: String) {


    }

    override fun destroy() {
        disruptor.shutdown()
    }

    override fun afterPropertiesSet() {
        disruptor = Disruptor<MessageEvent>(messageEventFactory, RING_BUFFER_SIZE, Executors.newCachedThreadPool(), ProducerType.MULTI, BlockingWaitStrategy() )

        disruptor.start()

    }
}