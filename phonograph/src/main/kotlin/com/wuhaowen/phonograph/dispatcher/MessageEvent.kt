package com.wuhaowen.phonograph.dispatcher

import com.lmax.disruptor.*
import com.wuhaowen.phonograph.dispatcher.mq.RabbitMqConf
import com.wuhaowen.phonograph.entity.Message
import com.wuhaowen.phonograph.proto.MessageOuterClass
import com.wuhaowen.phonograph.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.util.concurrent.ThreadFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate




@Component
class MessageEventFactory : EventFactory<Message> {

    override fun newInstance() = Message()
}


@Component
class MessageEventThreadFactory : ThreadFactory {
    override fun newThread(r: Runnable?): Thread {
        val t = Thread(r)
        t.name = "Message-Disruptor"
        t.isDaemon = true
        return t
    }
}

@Component
class MessageEventTranslator : EventTranslatorThreeArg<Message, Long, String, MessageOuterClass.Message> {
    override fun translateTo(event: Message, sequence: Long, messageId: Long, user: String?, messageProto: MessageOuterClass.Message) {
        event.run {
            msg = messageProto.message
            userId = user ?: messageProto.userId
            tag = messageProto.tag
            fromUser = messageProto.fromUser
            msgId = messageId
        }


    }
}

@Component
class MessageEventSaveHandler : EventHandler<Message> {
    @Autowired
    lateinit var repository: MessageRepository

    override fun onEvent(event: Message, sequence: Long, endOfBatch: Boolean) {
        repository.save(event)
    }

}

@Component
class MessageEventMQHandler : EventHandler<Message> {
    @Autowired lateinit var rabbitTemplate: RabbitTemplate
    @Autowired lateinit var conf: RabbitMqConf

    override fun onEvent(event: Message, sequence: Long, endOfBatch: Boolean) {
        rabbitTemplate.convertAndSend(conf.mqExchangeName, conf.routerKey, event)
    }
}

@Component
@ConfigurationProperties(prefix = "phonograph")
class DisruptorConfig {
    var messageRingsize: Int = 0
}