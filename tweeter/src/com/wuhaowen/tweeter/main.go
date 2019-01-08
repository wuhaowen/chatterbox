package main

import (
	"com/wuhaowen/tweeter/conn"
	"com/wuhaowen/tweeter/rpc"
	"github.com/spf13/viper"
	"log"
	"net/http"
)
import _ "net/http/pprof"


func main() {
	log.Println("tweeter start running")
	viper.AutomaticEnv()
	viper.AddConfigPath(".")
	viper.SetConfigName("tweeter")
	viper.SetConfigType("yaml")
	err := viper.ReadInConfig()
	if err != nil {
		log.Panic("找不到配置文件 tweeter.yaml")
	}
	port := viper.GetInt("server.port")
	rpcPort := viper.GetInt("server.grpc-port")
	rpcService := rpc.NewRpcService(rpcPort)
	go rpcService.StartService()
	go http.ListenAndServe(":9999", nil)
	server := conn.NewServer(port)
	server.Start()
}
