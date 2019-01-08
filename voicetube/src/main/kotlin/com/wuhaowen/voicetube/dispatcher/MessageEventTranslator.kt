package com.wuhaowen.voicetube.dispatcher

import com.lmax.disruptor.EventTranslatorThreeArg

class MessageEventTranslator : EventTranslatorThreeArg<MessageEvent, String, Long, Long>{

    override fun translateTo(event: MessageEvent?, sequence: Long, arg0: String?, arg1: Long?, arg2: Long?) {
    }
}