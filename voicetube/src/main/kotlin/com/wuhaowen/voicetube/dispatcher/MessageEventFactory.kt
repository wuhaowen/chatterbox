package com.wuhaowen.voicetube.dispatcher

import com.lmax.disruptor.EventFactory
import org.springframework.stereotype.Component

@Component
class MessageEventFactory : EventFactory<MessageEvent> {

    override fun newInstance(): MessageEvent {
        return MessageEvent()
    }
}