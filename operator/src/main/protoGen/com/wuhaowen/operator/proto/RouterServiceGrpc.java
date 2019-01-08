package com.wuhaowen.operator.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: rpc.proto")
public final class RouterServiceGrpc {

  private RouterServiceGrpc() {}

  public static final String SERVICE_NAME = "msg.RouterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.wuhaowen.operator.proto.Rpc.RouterKey,
      com.wuhaowen.operator.proto.Rpc.RouterResp> getPutRouterRuleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PutRouterRule",
      requestType = com.wuhaowen.operator.proto.Rpc.RouterKey.class,
      responseType = com.wuhaowen.operator.proto.Rpc.RouterResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.wuhaowen.operator.proto.Rpc.RouterKey,
      com.wuhaowen.operator.proto.Rpc.RouterResp> getPutRouterRuleMethod() {
    io.grpc.MethodDescriptor<com.wuhaowen.operator.proto.Rpc.RouterKey, com.wuhaowen.operator.proto.Rpc.RouterResp> getPutRouterRuleMethod;
    if ((getPutRouterRuleMethod = RouterServiceGrpc.getPutRouterRuleMethod) == null) {
      synchronized (RouterServiceGrpc.class) {
        if ((getPutRouterRuleMethod = RouterServiceGrpc.getPutRouterRuleMethod) == null) {
          RouterServiceGrpc.getPutRouterRuleMethod = getPutRouterRuleMethod = 
              io.grpc.MethodDescriptor.<com.wuhaowen.operator.proto.Rpc.RouterKey, com.wuhaowen.operator.proto.Rpc.RouterResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "msg.RouterService", "PutRouterRule"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.wuhaowen.operator.proto.Rpc.RouterKey.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.wuhaowen.operator.proto.Rpc.RouterResp.getDefaultInstance()))
                  .setSchemaDescriptor(new RouterServiceMethodDescriptorSupplier("PutRouterRule"))
                  .build();
          }
        }
     }
     return getPutRouterRuleMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RouterServiceStub newStub(io.grpc.Channel channel) {
    return new RouterServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RouterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RouterServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RouterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RouterServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class RouterServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void putRouterRule(com.wuhaowen.operator.proto.Rpc.RouterKey request,
        io.grpc.stub.StreamObserver<com.wuhaowen.operator.proto.Rpc.RouterResp> responseObserver) {
      asyncUnimplementedUnaryCall(getPutRouterRuleMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPutRouterRuleMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.wuhaowen.operator.proto.Rpc.RouterKey,
                com.wuhaowen.operator.proto.Rpc.RouterResp>(
                  this, METHODID_PUT_ROUTER_RULE)))
          .build();
    }
  }

  /**
   */
  public static final class RouterServiceStub extends io.grpc.stub.AbstractStub<RouterServiceStub> {
    private RouterServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RouterServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RouterServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RouterServiceStub(channel, callOptions);
    }

    /**
     */
    public void putRouterRule(com.wuhaowen.operator.proto.Rpc.RouterKey request,
        io.grpc.stub.StreamObserver<com.wuhaowen.operator.proto.Rpc.RouterResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPutRouterRuleMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RouterServiceBlockingStub extends io.grpc.stub.AbstractStub<RouterServiceBlockingStub> {
    private RouterServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RouterServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RouterServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RouterServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.wuhaowen.operator.proto.Rpc.RouterResp putRouterRule(com.wuhaowen.operator.proto.Rpc.RouterKey request) {
      return blockingUnaryCall(
          getChannel(), getPutRouterRuleMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RouterServiceFutureStub extends io.grpc.stub.AbstractStub<RouterServiceFutureStub> {
    private RouterServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RouterServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RouterServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RouterServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.wuhaowen.operator.proto.Rpc.RouterResp> putRouterRule(
        com.wuhaowen.operator.proto.Rpc.RouterKey request) {
      return futureUnaryCall(
          getChannel().newCall(getPutRouterRuleMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PUT_ROUTER_RULE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RouterServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RouterServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PUT_ROUTER_RULE:
          serviceImpl.putRouterRule((com.wuhaowen.operator.proto.Rpc.RouterKey) request,
              (io.grpc.stub.StreamObserver<com.wuhaowen.operator.proto.Rpc.RouterResp>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RouterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RouterServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.wuhaowen.operator.proto.Rpc.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RouterService");
    }
  }

  private static final class RouterServiceFileDescriptorSupplier
      extends RouterServiceBaseDescriptorSupplier {
    RouterServiceFileDescriptorSupplier() {}
  }

  private static final class RouterServiceMethodDescriptorSupplier
      extends RouterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RouterServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RouterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RouterServiceFileDescriptorSupplier())
              .addMethod(getPutRouterRuleMethod())
              .build();
        }
      }
    }
    return result;
  }
}
