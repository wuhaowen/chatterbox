package com.wuhaowen.voicetube.dispatcher

import com.lmax.disruptor.EventHandler

/**
 * 存储消息
 */
class MessageEventPersistenceHandler : EventHandler<MessageEvent> {

    override fun onEvent(event: MessageEvent?, sequence: Long, endOfBatch: Boolean) {

    }
}