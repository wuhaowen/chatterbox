package com.wuhaowen.operator.service

import com.wuhaowen.operator.proto.RouterServiceGrpc
import com.wuhaowen.operator.proto.Rpc
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService


/**
 * @program: operator
 * @description: 存储route rule
 * @author: wuhaowen
 * @create: 2018-11-2018/11/1
 **/
@GRpcService
class RouterService : RouterServiceGrpc.RouterServiceImplBase(){

    override fun putRouterRule(request: Rpc.RouterKey?, responseObserver: StreamObserver<Rpc.RouterResp>?) {
        val response = Rpc.RouterResp.newBuilder().setSuccess(true).build()
        responseObserver?.run {
            onNext(response)
            onCompleted()
        }
    }


}