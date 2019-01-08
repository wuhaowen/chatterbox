package conn

import (
	"bytes"
	"encoding/binary"
	"github.com/golang/protobuf/proto"
	"net"
	"sync"
)

const HEADER uint8 = 0x66
const VERSION uint8 = 1

const (
	TYPE_PING          uint8 = 1
	TYPE_PONG          uint8 = 2
	TYPE_MESSAGE       uint8 = 3
	TYPE_MESSAGE_REPLY uint8 = 4 //客户端消息反馈
	TYPE_JOIN          uint8 = 5
	TYPE_JOIN_REPLY    uint8 = 6
)

type Channel struct {
	Id string
	Connection  *net.Conn
	sync.Mutex
}

func (c *Channel) Push(msg proto.Message, msgType uint8) (err error) {
	c.Lock()
	defer c.Unlock()
	rawMsg, err := proto.Marshal(msg)
	if err != nil {
		return err
	}
	bodySize := uint16(len(rawMsg))
	var m = new(bytes.Buffer)
	err = binary.Write(m, binary.BigEndian, HEADER)
	err = binary.Write(m, binary.BigEndian, VERSION)
	err = binary.Write(m, binary.BigEndian, msgType)
	err = binary.Write(m, binary.BigEndian, bodySize)
	conn := *c.Connection
	_,err = conn.Write(m.Bytes())
	_,err = conn.Write(rawMsg)
	return
}
