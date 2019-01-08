package com.wuhaowen.operator.service

import com.wuhaowen.operator.proto.MessageServiceGrpc
import com.wuhaowen.operator.proto.TweetOuterClass
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * @program: operator
 * @description: 消息发送客户端
 * @author: wuhaowen
 * @create: 2018-11-2018/11/1
 **/
class TweeterClient(val path :String){
    private var channel: ManagedChannel = ManagedChannelBuilder.forTarget(path).usePlaintext().build()
    private var client: MessageServiceGrpc.MessageServiceFutureStub = MessageServiceGrpc.newFutureStub(channel)

    var seq = 0L

    init {
        test()
    }

    fun test(){
        Timer(true).schedule(object :TimerTask(){
            override fun run() {
                test()
            }

        }, 3000)
        send()
    }

    fun send(){
        seq++
        val msg = TweetOuterClass.Message.newBuilder().setMessage("test$seq").setSeq(seq).setUserId("test123").build()
        client.messageHandle(msg)
    }

    fun stop(){
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }


}