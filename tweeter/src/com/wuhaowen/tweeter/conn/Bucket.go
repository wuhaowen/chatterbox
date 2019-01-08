package conn

import (
	"com/wuhaowen/tweeter/proto"
	"log"
	"sync"
	"time"
)

type Bucket struct {
	//Conns      sync.Map
	Conns 		map[string]*Channel
	sync.RWMutex
	Size       int32
	manager *BucketManager
}

func NewBucket(manager *BucketManager) (bucket *Bucket) {
	bucket = new(Bucket)
	bucket.Size = 0
	bucket.Conns = make(map[string]*Channel)
	bucket.manager = manager
	return
}



func (b *Bucket) Put(conn *Channel) {
	b.Lock()
	defer b.Unlock()
	if b.Conns[conn.Id] == nil {
		b.Size++
	}
	b.Conns[conn.Id] = conn
}

func (b *Bucket) Del(conn *Channel) {
	b.Lock()
	defer b.Unlock()
	delete(b.Conns, conn.Id)
	b.Size--
}

func (b *Bucket) DelByID(userId string) {
	b.Lock()
	defer b.Unlock()
	delete(b.Conns, userId)
	b.Size--
}


func (b *Bucket)PushTo(msg *msg.Message) bool  {
 	b.RLock()
 	defer b.RUnlock()
 	v := b.Conns[msg.UserId]
	if v == nil {
		log.Println("找不到userid:{}", msg.UserId)
		return false
	}else {
		(*v.Connection).SetWriteDeadline(time.Now().Add(time.Second * 4))
		defer (*v.Connection).SetWriteDeadline(time.Time{})
		return v.Push(msg, TYPE_MESSAGE) == nil
	}
}
