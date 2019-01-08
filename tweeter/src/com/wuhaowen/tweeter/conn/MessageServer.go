package conn

import (
	"bytes"
	"com/wuhaowen/tweeter/proto"
	"context"
	"encoding/binary"
	"fmt"
	"github.com/golang/protobuf/proto"
	"io"
	"log"
	"net"
	"os"
	"os/signal"
	"sync"
	"syscall"
	"time"
)

type MessageServer struct {
	Port     int
	buckets  sync.Map
}

func NewServer(port int) (server *MessageServer) {
	server = new(MessageServer)
	server.Port = port
	return
}

func (s *MessageServer) split(data []byte, atEOF bool) (advance int, token []byte, err error) {
	if !atEOF && data[0] == HEADER {
		if len(data) > 5 {
			var bodySize int16
			binary.Read(bytes.NewReader(data[3:5]), binary.BigEndian, &bodySize)
			if int(bodySize)+5 <= len(data) {
				return int(bodySize) + 5, data[:int(bodySize)+5 ], nil
			}
		}
	}
	return
}

func (s *MessageServer) Start() {
	addr := fmt.Sprintf("0.0.0.0:%d", s.Port)

	tcpAddr, err := net.ResolveTCPAddr("tcp", addr)
	if err != nil {
		log.Fatalf("net.ResovleTCPAddr fail:%s", addr)
	}

	l, err := net.ListenTCP("tcp", tcpAddr)
	if err != nil {
		log.Panic("启动服务器错误！！", err)
	}
	defer l.Close()
	go s.listen(l)
	log.Println("tweeter running.....")

	quit := make(chan os.Signal)
	signal.Notify(quit, syscall.SIGTERM, syscall.SIGINT, syscall.SIGKILL, syscall.SIGHUP, syscall.SIGQUIT)

	<-quit
}

func (s *MessageServer) listen(l net.Listener) {
	for {
		conn, err := l.Accept()
		if err != nil {
			log.Println("接受连接发生错误", err)
		} else {
			go s.handleConn(&conn)
		}
	}
}




func (s *MessageServer) handleConn(conn *net.Conn) {
	c := *conn
	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()
	buffer := make([]byte, 5)
	var channel *Channel
	for {
		select {
		case <-ctx.Done():
			c.Close()
			if channel != nil {
				GetBucketManager().DelById(channel.Id)
				channel = nil
			}
			return
		default:
			//设置一分钟超时时间
			if err := c.SetReadDeadline(time.Now().Add(time.Minute * 1)); err != nil {
				cancel()
				continue
			}
			n, err := io.ReadFull(c, buffer)
			if n < 5 || err != nil {
				cancel()
				continue
			}
			var bodySize uint16
			binary.Read(bytes.NewReader(buffer[3:]), binary.BigEndian, &bodySize)
			body := make([]byte, bodySize)
			n, err = io.ReadFull(c, body)
			if n < int(bodySize) || err != nil {
				cancel()
				continue
			}
			var t uint8
			binary.Read(bytes.NewReader(buffer[2:3]), binary.BigEndian, &t)
			switch t {
			case TYPE_PING:
				log.Println("recv ping")
				go s.pong(channel, &cancel)
				break
			case TYPE_JOIN:
				log.Println("recv join")
				knock := &msg.Knock{}
				if proto.Unmarshal(body, knock) != nil {
					cancel()
					continue
				}
				channel = new(Channel)
				channel.Id = knock.UserId
				channel.Connection = conn
				go s.joinReply(channel, &cancel)

				break
			case TYPE_MESSAGE_REPLY:
				reply := &msg.MessageReply{}
				if proto.Unmarshal(body, reply) != nil {
					cancel()
					continue
				}
				//todo ack 消息确认消费
				lastSeq := reply.LastSeq
				log.Printf("message ack %d", lastSeq)
				break
			}
		}
	}

}

func (s *MessageServer) pong(channel *Channel, cancel *context.CancelFunc) {
	tweet := &msg.Tweet{}
	tweet.Echo = 2
	log.Println("send pong")
	(*channel.Connection).SetWriteDeadline(time.Now().Add(time.Second * 4))
	if channel.Push(tweet, TYPE_PONG) != nil {
		(*cancel)()
	}
	(*channel.Connection).SetWriteDeadline(time.Time{})

}

func (s *MessageServer) joinReply(channel *Channel, cancel *context.CancelFunc) {
	tweet := &msg.Tweet{}
	tweet.Echo = 1
	log.Println("send joinReply")
	(*channel.Connection).SetWriteDeadline(time.Now().Add(time.Second * 4))
	if channel.Push(tweet, TYPE_JOIN_REPLY) != nil {
		(*cancel)()
	}
	(*channel.Connection).SetWriteDeadline(time.Time{})
	GetBucketManager().Put(channel)

}
