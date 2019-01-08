package rpc

import (
	"com/wuhaowen/tweeter/conn"
	"com/wuhaowen/tweeter/proto"
	"context"
	"fmt"
	"google.golang.org/grpc"
	"log"
	"net"
)

type RpcService struct {
	port int
}

func NewRpcService(port int) (rpcService *RpcService)  {
	rpcService = new(RpcService)
	rpcService.port = port
	return
}

func (s *RpcService) StartService()  {
	log.Printf("start rpc service...")
	lis, err := net.Listen("tcp", fmt.Sprintf(":%d", s.port))
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	grpcServer := grpc.NewServer()
	msg.RegisterMessageServiceServer(grpcServer, s)
	grpcServer.Serve(lis)
}

func (s *RpcService) MessageHandle(ctx context.Context, m *msg.Message) (*msg.MessageResp, error){
	conn.GetBucketManager().PushTo(m)
	log.Printf("user：%s  message: %s", m.UserId, m.Message)
	return &msg.MessageResp{Success:true},nil
}
