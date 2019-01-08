package conn

import (
	"com/wuhaowen/tweeter/proto"
	"github.com/spf13/viper"
	"hash/fnv"
	"log"
	"sync"
)

type BucketManager struct {
	Size uint32
	Buckets []*Bucket
	ConnSize int32
	instanceId int
}

var m *BucketManager
var once sync.Once

func init() {
	once.Do(func() {
		log.Println("init bucket manager")
		viper.AutomaticEnv()
		viper.AddConfigPath(".")
		viper.SetConfigName("tweeter")
		viper.SetConfigType("yaml")
		err := viper.ReadInConfig()
		if err != nil {
			log.Panic("找不到配置文件 tweeter.yaml")
		}
		size := viper.GetInt("server.bucket-size")
		instanceId := viper.GetInt("server.instance-id")
		if size == 0 {
			size = 200
		}
		m = new(BucketManager)
		m.Size = uint32(size)
		m.instanceId = instanceId
		m.Buckets = make([]*Bucket,size)
		for i:=0;i < size ; i++ {
			m.Buckets[i] = NewBucket(m)
		}

	})
}

func GetBucketManager() *BucketManager  {
	return m
}

func (b *BucketManager)PushTo(msg *msg.Message) bool {
	index := b.indexBucket(msg.UserId)
	return b.Buckets[index].PushTo(msg)
}

func (b *BucketManager)Put(conn *Channel) {
	index := b.indexBucket(conn.Id)
	b.Buckets[index].Put(conn)
}

func (b *BucketManager)DelById(userId string)  {
	index := b.indexBucket(userId)
	b.Buckets[index].DelByID(userId)
	log.Printf("userId %s leave", userId)
}

func (b *BucketManager)indexBucket(s string) uint32  {
	h := fnv.New32a()
	h.Write([]byte(s))
	return h.Sum32()%b.Size
}

