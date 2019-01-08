package com.wuhaowen.phonograph.proto;

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
    comments = "Source: message.proto")
public final class MessageServiceGrpc {

  private MessageServiceGrpc() {}

  public static final String SERVICE_NAME = "MessageService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.wuhaowen.phonograph.proto.MessageOuterClass.Message,
      com.wuhaowen.phonograph.proto.MessageOuterClass.Result> getSaveMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "saveMessage",
      requestType = com.wuhaowen.phonograph.proto.MessageOuterClass.Message.class,
      responseType = com.wuhaowen.phonograph.proto.MessageOuterClass.Result.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.wuhaowen.phonograph.proto.MessageOuterClass.Message,
      com.wuhaowen.phonograph.proto.MessageOuterClass.Result> getSaveMessageMethod() {
    io.grpc.MethodDescriptor<com.wuhaowen.phonograph.proto.MessageOuterClass.Message, com.wuhaowen.phonograph.proto.MessageOuterClass.Result> getSaveMessageMethod;
    if ((getSaveMessageMethod = MessageServiceGrpc.getSaveMessageMethod) == null) {
      synchronized (MessageServiceGrpc.class) {
        if ((getSaveMessageMethod = MessageServiceGrpc.getSaveMessageMethod) == null) {
          MessageServiceGrpc.getSaveMessageMethod = getSaveMessageMethod = 
              io.grpc.MethodDescriptor.<com.wuhaowen.phonograph.proto.MessageOuterClass.Message, com.wuhaowen.phonograph.proto.MessageOuterClass.Result>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "MessageService", "saveMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.wuhaowen.phonograph.proto.MessageOuterClass.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.wuhaowen.phonograph.proto.MessageOuterClass.Result.getDefaultInstance()))
                  .setSchemaDescriptor(new MessageServiceMethodDescriptorSupplier("saveMessage"))
                  .build();
          }
        }
     }
     return getSaveMessageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MessageServiceStub newStub(io.grpc.Channel channel) {
    return new MessageServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MessageServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MessageServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MessageServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MessageServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class MessageServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void saveMessage(com.wuhaowen.phonograph.proto.MessageOuterClass.Message request,
        io.grpc.stub.StreamObserver<com.wuhaowen.phonograph.proto.MessageOuterClass.Result> responseObserver) {
      asyncUnimplementedUnaryCall(getSaveMessageMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSaveMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.wuhaowen.phonograph.proto.MessageOuterClass.Message,
                com.wuhaowen.phonograph.proto.MessageOuterClass.Result>(
                  this, METHODID_SAVE_MESSAGE)))
          .build();
    }
  }

  /**
   */
  public static final class MessageServiceStub extends io.grpc.stub.AbstractStub<MessageServiceStub> {
    private MessageServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessageServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessageServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessageServiceStub(channel, callOptions);
    }

    /**
     */
    public void saveMessage(com.wuhaowen.phonograph.proto.MessageOuterClass.Message request,
        io.grpc.stub.StreamObserver<com.wuhaowen.phonograph.proto.MessageOuterClass.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSaveMessageMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MessageServiceBlockingStub extends io.grpc.stub.AbstractStub<MessageServiceBlockingStub> {
    private MessageServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessageServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessageServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessageServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.wuhaowen.phonograph.proto.MessageOuterClass.Result saveMessage(com.wuhaowen.phonograph.proto.MessageOuterClass.Message request) {
      return blockingUnaryCall(
          getChannel(), getSaveMessageMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MessageServiceFutureStub extends io.grpc.stub.AbstractStub<MessageServiceFutureStub> {
    private MessageServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessageServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessageServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessageServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.wuhaowen.phonograph.proto.MessageOuterClass.Result> saveMessage(
        com.wuhaowen.phonograph.proto.MessageOuterClass.Message request) {
      return futureUnaryCall(
          getChannel().newCall(getSaveMessageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAVE_MESSAGE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MessageServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MessageServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAVE_MESSAGE:
          serviceImpl.saveMessage((com.wuhaowen.phonograph.proto.MessageOuterClass.Message) request,
              (io.grpc.stub.StreamObserver<com.wuhaowen.phonograph.proto.MessageOuterClass.Result>) responseObserver);
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

  private static abstract class MessageServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MessageServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.wuhaowen.phonograph.proto.MessageOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MessageService");
    }
  }

  private static final class MessageServiceFileDescriptorSupplier
      extends MessageServiceBaseDescriptorSupplier {
    MessageServiceFileDescriptorSupplier() {}
  }

  private static final class MessageServiceMethodDescriptorSupplier
      extends MessageServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MessageServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (MessageServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MessageServiceFileDescriptorSupplier())
              .addMethod(getSaveMessageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
