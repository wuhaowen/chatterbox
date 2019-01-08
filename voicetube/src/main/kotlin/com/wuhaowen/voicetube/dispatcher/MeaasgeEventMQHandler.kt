package com.wuhaowen.voicetube.dispatcher

import com.lmax.disruptor.EventHandler

/**
 * 发送消息
 */
class MeaasgeEventMQHandler : EventHandler<MessageEvent> {

    override fun onEvent(event: MessageEvent?, sequence: Long, endOfBatch: Boolean) {


    }

}