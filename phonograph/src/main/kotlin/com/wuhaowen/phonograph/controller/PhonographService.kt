package com.wuhaowen.phonograph.controller

import com.wuhaowen.phonograph.dispatcher.MessageDispatcher
import com.wuhaowen.phonograph.proto.MessageOuterClass
import com.wuhaowen.phonograph.proto.MessageServiceGrpc
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.springframework.beans.factory.annotation.Autowired


@GRpcService
class PhonographService : MessageServiceGrpc.MessageServiceImplBase() {

    @Autowired lateinit var dispatcher: MessageDispatcher

    override fun saveMessage(request: MessageOuterClass.Message, responseObserver: StreamObserver<MessageOuterClass.Result>) {
        dispatcher.dispatch(request)

    }
}
