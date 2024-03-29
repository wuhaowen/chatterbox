// Code generated by protoc-gen-go. DO NOT EDIT.
// source: tweet.proto

package msg

import (
	fmt "fmt"
	proto "github.com/golang/protobuf/proto"
	context "golang.org/x/net/context"
	grpc "google.golang.org/grpc"
	math "math"
)

// Reference imports to suppress errors if they are not otherwise used.
var _ = proto.Marshal
var _ = fmt.Errorf
var _ = math.Inf

// This is a compile-time assertion to ensure that this generated file
// is compatible with the proto package it is being compiled against.
// A compilation error at this line likely means your copy of the
// proto package needs to be updated.
const _ = proto.ProtoPackageIsVersion2 // please upgrade the proto package

type Knock struct {
	UserId               string   `protobuf:"bytes,1,opt,name=userId,proto3" json:"userId,omitempty"`
	Tag                  string   `protobuf:"bytes,2,opt,name=tag,proto3" json:"tag,omitempty"`
	LastSeq              int64    `protobuf:"varint,3,opt,name=lastSeq,proto3" json:"lastSeq,omitempty"`
	AppKey               string   `protobuf:"bytes,4,opt,name=appKey,proto3" json:"appKey,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *Knock) Reset()         { *m = Knock{} }
func (m *Knock) String() string { return proto.CompactTextString(m) }
func (*Knock) ProtoMessage()    {}
func (*Knock) Descriptor() ([]byte, []int) {
	return fileDescriptor_3ff920f5c96009e6, []int{0}
}

func (m *Knock) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_Knock.Unmarshal(m, b)
}
func (m *Knock) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_Knock.Marshal(b, m, deterministic)
}
func (m *Knock) XXX_Merge(src proto.Message) {
	xxx_messageInfo_Knock.Merge(m, src)
}
func (m *Knock) XXX_Size() int {
	return xxx_messageInfo_Knock.Size(m)
}
func (m *Knock) XXX_DiscardUnknown() {
	xxx_messageInfo_Knock.DiscardUnknown(m)
}

var xxx_messageInfo_Knock proto.InternalMessageInfo

func (m *Knock) GetUserId() string {
	if m != nil {
		return m.UserId
	}
	return ""
}

func (m *Knock) GetTag() string {
	if m != nil {
		return m.Tag
	}
	return ""
}

func (m *Knock) GetLastSeq() int64 {
	if m != nil {
		return m.LastSeq
	}
	return 0
}

func (m *Knock) GetAppKey() string {
	if m != nil {
		return m.AppKey
	}
	return ""
}

type Message struct {
	UserId               string   `protobuf:"bytes,1,opt,name=userId,proto3" json:"userId,omitempty"`
	Tag                  string   `protobuf:"bytes,2,opt,name=tag,proto3" json:"tag,omitempty"`
	Seq                  int64    `protobuf:"varint,3,opt,name=seq,proto3" json:"seq,omitempty"`
	Message              string   `protobuf:"bytes,4,opt,name=message,proto3" json:"message,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *Message) Reset()         { *m = Message{} }
func (m *Message) String() string { return proto.CompactTextString(m) }
func (*Message) ProtoMessage()    {}
func (*Message) Descriptor() ([]byte, []int) {
	return fileDescriptor_3ff920f5c96009e6, []int{1}
}

func (m *Message) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_Message.Unmarshal(m, b)
}
func (m *Message) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_Message.Marshal(b, m, deterministic)
}
func (m *Message) XXX_Merge(src proto.Message) {
	xxx_messageInfo_Message.Merge(m, src)
}
func (m *Message) XXX_Size() int {
	return xxx_messageInfo_Message.Size(m)
}
func (m *Message) XXX_DiscardUnknown() {
	xxx_messageInfo_Message.DiscardUnknown(m)
}

var xxx_messageInfo_Message proto.InternalMessageInfo

func (m *Message) GetUserId() string {
	if m != nil {
		return m.UserId
	}
	return ""
}

func (m *Message) GetTag() string {
	if m != nil {
		return m.Tag
	}
	return ""
}

func (m *Message) GetSeq() int64 {
	if m != nil {
		return m.Seq
	}
	return 0
}

func (m *Message) GetMessage() string {
	if m != nil {
		return m.Message
	}
	return ""
}

type MessageResp struct {
	Success              bool     `protobuf:"varint,1,opt,name=success,proto3" json:"success,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *MessageResp) Reset()         { *m = MessageResp{} }
func (m *MessageResp) String() string { return proto.CompactTextString(m) }
func (*MessageResp) ProtoMessage()    {}
func (*MessageResp) Descriptor() ([]byte, []int) {
	return fileDescriptor_3ff920f5c96009e6, []int{2}
}

func (m *MessageResp) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_MessageResp.Unmarshal(m, b)
}
func (m *MessageResp) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_MessageResp.Marshal(b, m, deterministic)
}
func (m *MessageResp) XXX_Merge(src proto.Message) {
	xxx_messageInfo_MessageResp.Merge(m, src)
}
func (m *MessageResp) XXX_Size() int {
	return xxx_messageInfo_MessageResp.Size(m)
}
func (m *MessageResp) XXX_DiscardUnknown() {
	xxx_messageInfo_MessageResp.DiscardUnknown(m)
}

var xxx_messageInfo_MessageResp proto.InternalMessageInfo

func (m *MessageResp) GetSuccess() bool {
	if m != nil {
		return m.Success
	}
	return false
}

type MessageReply struct {
	LastSeq              int64    `protobuf:"varint,1,opt,name=lastSeq,proto3" json:"lastSeq,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *MessageReply) Reset()         { *m = MessageReply{} }
func (m *MessageReply) String() string { return proto.CompactTextString(m) }
func (*MessageReply) ProtoMessage()    {}
func (*MessageReply) Descriptor() ([]byte, []int) {
	return fileDescriptor_3ff920f5c96009e6, []int{3}
}

func (m *MessageReply) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_MessageReply.Unmarshal(m, b)
}
func (m *MessageReply) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_MessageReply.Marshal(b, m, deterministic)
}
func (m *MessageReply) XXX_Merge(src proto.Message) {
	xxx_messageInfo_MessageReply.Merge(m, src)
}
func (m *MessageReply) XXX_Size() int {
	return xxx_messageInfo_MessageReply.Size(m)
}
func (m *MessageReply) XXX_DiscardUnknown() {
	xxx_messageInfo_MessageReply.DiscardUnknown(m)
}

var xxx_messageInfo_MessageReply proto.InternalMessageInfo

func (m *MessageReply) GetLastSeq() int64 {
	if m != nil {
		return m.LastSeq
	}
	return 0
}

type Tweet struct {
	Echo                 int64    `protobuf:"varint,1,opt,name=echo,proto3" json:"echo,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *Tweet) Reset()         { *m = Tweet{} }
func (m *Tweet) String() string { return proto.CompactTextString(m) }
func (*Tweet) ProtoMessage()    {}
func (*Tweet) Descriptor() ([]byte, []int) {
	return fileDescriptor_3ff920f5c96009e6, []int{4}
}

func (m *Tweet) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_Tweet.Unmarshal(m, b)
}
func (m *Tweet) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_Tweet.Marshal(b, m, deterministic)
}
func (m *Tweet) XXX_Merge(src proto.Message) {
	xxx_messageInfo_Tweet.Merge(m, src)
}
func (m *Tweet) XXX_Size() int {
	return xxx_messageInfo_Tweet.Size(m)
}
func (m *Tweet) XXX_DiscardUnknown() {
	xxx_messageInfo_Tweet.DiscardUnknown(m)
}

var xxx_messageInfo_Tweet proto.InternalMessageInfo

func (m *Tweet) GetEcho() int64 {
	if m != nil {
		return m.Echo
	}
	return 0
}

func init() {
	proto.RegisterType((*Knock)(nil), "msg.Knock")
	proto.RegisterType((*Message)(nil), "msg.Message")
	proto.RegisterType((*MessageResp)(nil), "msg.MessageResp")
	proto.RegisterType((*MessageReply)(nil), "msg.MessageReply")
	proto.RegisterType((*Tweet)(nil), "msg.Tweet")
}

func init() { proto.RegisterFile("tweet.proto", fileDescriptor_3ff920f5c96009e6) }

var fileDescriptor_3ff920f5c96009e6 = []byte{
	// 244 bytes of a gzipped FileDescriptorProto
	0x1f, 0x8b, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0xff, 0x94, 0x91, 0x3f, 0x4f, 0xc3, 0x30,
	0x10, 0xc5, 0x15, 0xdc, 0x3f, 0x70, 0x2d, 0x28, 0xba, 0x01, 0x59, 0xb0, 0x54, 0x5e, 0xc8, 0x14,
	0x24, 0xf8, 0x04, 0x6c, 0xa0, 0x8a, 0x25, 0x65, 0x47, 0xc6, 0x3d, 0x05, 0x44, 0x52, 0x9b, 0x9c,
	0x0b, 0xea, 0xb7, 0x47, 0x76, 0x9c, 0x2a, 0x8c, 0x6c, 0xef, 0xf7, 0x7c, 0x7a, 0xcf, 0x67, 0xc3,
	0xc2, 0xff, 0x10, 0xf9, 0xd2, 0x75, 0xd6, 0x5b, 0x14, 0x2d, 0xd7, 0xca, 0xc0, 0x74, 0xbd, 0xb3,
	0xe6, 0x13, 0x2f, 0x61, 0xb6, 0x67, 0xea, 0x9e, 0xb6, 0x32, 0x5b, 0x65, 0xc5, 0x59, 0x95, 0x08,
	0x73, 0x10, 0x5e, 0xd7, 0xf2, 0x24, 0x9a, 0x41, 0xa2, 0x84, 0x79, 0xa3, 0xd9, 0x6f, 0xe8, 0x4b,
	0x8a, 0x55, 0x56, 0x88, 0x6a, 0xc0, 0x90, 0xa1, 0x9d, 0x5b, 0xd3, 0x41, 0x4e, 0xfa, 0x8c, 0x9e,
	0xd4, 0x2b, 0xcc, 0x9f, 0x89, 0x59, 0xd7, 0xf4, 0x8f, 0x9a, 0x1c, 0x04, 0x1f, 0x2b, 0x82, 0x0c,
	0xc5, 0x6d, 0x1f, 0x93, 0xf2, 0x07, 0x54, 0x37, 0xb0, 0x48, 0x05, 0x15, 0xb1, 0x0b, 0x83, 0xbc,
	0x37, 0x86, 0x98, 0x63, 0xcb, 0x69, 0x35, 0xa0, 0x2a, 0x60, 0x79, 0x1c, 0x74, 0xcd, 0x61, 0xbc,
	0x4b, 0xf6, 0x67, 0x17, 0x75, 0x0d, 0xd3, 0x97, 0xf0, 0x58, 0x88, 0x30, 0x21, 0xf3, 0x6e, 0xd3,
	0x79, 0xd4, 0x77, 0x0f, 0x70, 0x91, 0x62, 0x36, 0xd4, 0x7d, 0x7f, 0x18, 0xc2, 0x5b, 0x38, 0x4f,
	0xce, 0xa3, 0xde, 0x6d, 0x1b, 0xc2, 0x65, 0xd9, 0x72, 0x5d, 0x26, 0xef, 0x2a, 0x1f, 0x53, 0xb8,
	0xe3, 0xdb, 0x2c, 0x7e, 0xc2, 0xfd, 0x6f, 0x00, 0x00, 0x00, 0xff, 0xff, 0x70, 0x06, 0x55, 0xc3,
	0x93, 0x01, 0x00, 0x00,
}

// Reference imports to suppress errors if they are not otherwise used.
var _ context.Context
var _ grpc.ClientConn

// This is a compile-time assertion to ensure that this generated file
// is compatible with the grpc package it is being compiled against.
const _ = grpc.SupportPackageIsVersion4

// MessageServiceClient is the client API for MessageService service.
//
// For semantics around ctx use and closing/ending streaming RPCs, please refer to https://godoc.org/google.golang.org/grpc#ClientConn.NewStream.
type MessageServiceClient interface {
	MessageHandle(ctx context.Context, in *Message, opts ...grpc.CallOption) (*MessageResp, error)
}

type messageServiceClient struct {
	cc *grpc.ClientConn
}

func NewMessageServiceClient(cc *grpc.ClientConn) MessageServiceClient {
	return &messageServiceClient{cc}
}

func (c *messageServiceClient) MessageHandle(ctx context.Context, in *Message, opts ...grpc.CallOption) (*MessageResp, error) {
	out := new(MessageResp)
	err := c.cc.Invoke(ctx, "/msg.MessageService/MessageHandle", in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}

// MessageServiceServer is the server API for MessageService service.
type MessageServiceServer interface {
	MessageHandle(context.Context, *Message) (*MessageResp, error)
}

func RegisterMessageServiceServer(s *grpc.Server, srv MessageServiceServer) {
	s.RegisterService(&_MessageService_serviceDesc, srv)
}

func _MessageService_MessageHandle_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(Message)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
		return srv.(MessageServiceServer).MessageHandle(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: "/msg.MessageService/MessageHandle",
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(MessageServiceServer).MessageHandle(ctx, req.(*Message))
	}
	return interceptor(ctx, in, info, handler)
}

var _MessageService_serviceDesc = grpc.ServiceDesc{
	ServiceName: "msg.MessageService",
	HandlerType: (*MessageServiceServer)(nil),
	Methods: []grpc.MethodDesc{
		{
			MethodName: "MessageHandle",
			Handler:    _MessageService_MessageHandle_Handler,
		},
	},
	Streams:  []grpc.StreamDesc{},
	Metadata: "tweet.proto",
}
