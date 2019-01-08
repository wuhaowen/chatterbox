package com.wuhaowen.meteor.conn

import android.content.Context
import android.util.Log
import com.wuhaowen.meteor.common.UserInfo
import com.wuhaowen.meteor.proto.TweetOuterClass
import com.wuhaowen.meteor.thread.TaskWriteThreadPool
import okio.*
import java.lang.Exception
import java.net.InetSocketAddress
import java.net.Socket
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

const val TYPE_PING = 1
const val TYPE_PONG = 2
const val TYPE_MESSAGE = 3
const val TYPE_MESSAGE_REPLY = 4
const val TYPE_JOIN = 5
const val TYPE_JOIN_REPLY = 6
const val TAG = "PUSH-SDK"

class Channel(private val host: String,
              private val port: Int,
              private val userInfo: UserInfo,
              private val context: Context) {
    private var socket: Socket? = null
    private var readThreadExecutorService :ExecutorService? = null
    private var heartBeatManager: HeartBeatManager? = null
    private var source:BufferedSource? = null
    private var sink:BufferedSink? = null
    private var reconnect = AtomicBoolean(true)
    private var reconnectInterval = 0L


    fun connect() {
        try {
            val inetSocketAddress = InetSocketAddress(host, port)
            socket = Socket().apply {
                keepAlive = true
                tcpNoDelay = true
                //setSoLinger(true, 0) // Control calls close () method,
                reuseAddress = true
                connect(inetSocketAddress, 5000)
                sink = sink().buffer()
            }

            heartBeatManager = HeartBeatManager(context,  pongTimeout = {
                //心跳回复超时
                Log.d(TAG, "ping timeout")

                resolveResource()

            }) {
                ping()
            }
            TaskWriteThreadPool.init()
            startReadData()
            join()

        } catch (e: Exception) {
            Log.e(TAG, "连接错误",e)

        }
    }

    private fun startReadData() {
        readThreadExecutorService = Executors.newSingleThreadExecutor()?.apply {
            submit {
                socket?.let {
                    Log.d(TAG, "读取线程启动")
                    val header = ByteArray(5)
                    source = it.source().buffer().apply {
                        while (!Thread.interrupted()) {
                            Log.d(TAG, "读取线程loop")
                            try {
                                readFully(header)
                                val type = header[2].toInt()
                                val size = (header[3].toInt() and 0xFF) shl 8 or (header[4].toInt() and 0xFF)
                                val body = ByteArray(size)
                                readFully(body)
                                when (type) {
                                    TYPE_PONG -> {
                                        Log.d(TAG, "recv pong")
                                        heartBeatManager?.schedulePing()
                                    }
                                    TYPE_JOIN_REPLY -> {
                                        Log.d(TAG, "recv joinReply")
                                        heartBeatManager?.schedulePing()
                                    }
                                    TYPE_MESSAGE -> {
                                        //发送回执 重新开启定时
                                        Log.d(TAG, "recv message")

                                        val msg = TweetOuterClass.Message.parseFrom(body)
                                        //todo 广播消息
                                        messageAck(msg.seq)

                                    }
                                }
                            } catch (e: InterruptedException) {
                                Thread.currentThread().interrupt()
                                Log.e(TAG, "read thread", e)

                            } catch (ex: Exception) {
                                Log.e(TAG, "read thread", ex)

                                break
                            }

                        }
                        //线程终止
                        Log.d(TAG, "read thread abort!")

                        resolveResource()


                    }

                }
            }
        }

    }

    private sealed class WriteCompletedType{
        object Ping : WriteCompletedType()
        object MessageReply : WriteCompletedType()
        object Knock : WriteCompletedType()

    }


    private fun join() {
        socket?.run {
            Log.d(TAG, "send join")
            TaskWriteThreadPool.submit(task = KnockTask(sink,
                    0,
                    userId = userInfo.userId,
                    tag = userInfo.tag,
                    appkey = userInfo.appkey) {
                onWriteCompleted(WriteCompletedType.Knock, it)
            })
        }
    }


    private fun ping() {
        socket?.run {
            Log.d(TAG, "send ping")
            TaskWriteThreadPool.submit(task = PingTask(sink) {
                onWriteCompleted(WriteCompletedType.Ping, it)
            })
        }
    }


    private fun messageAck(lastSeq: Long) {
        socket?.run {
            Log.d(TAG, "send ack")

            TaskWriteThreadPool.submit(task = MessageReplyTask(sink, lastSeq) {
                onWriteCompleted(WriteCompletedType.MessageReply, it)
            })
        }
    }

    private fun onWriteCompleted(type: WriteCompletedType, throwable: Throwable?) {
        throwable?.let {
            //发送数据出错
            //关闭连接
            Log.d(TAG, "send data error", it)

            resolveResource()

        }?:kotlin.run {
            when( type){
                is WriteCompletedType.Ping, WriteCompletedType.Knock -> {//ping完 4秒内必须要有回应
                    heartBeatManager?.waitPong()
                }
                else ->{


                }
            }


        }
    }

    @Synchronized
    private fun resolveResource(){
        heartBeatManager?.stopPing()
        readThreadExecutorService?.shutdownNow()
        TaskWriteThreadPool.shutdown()
        try {
            source?.close()
        }catch (_ :Exception){

        }finally {
            source = null
        }
        try {
            sink?.close()
        }catch (_ :Exception){

        }finally {
            sink = null
        }

        try {
            socket?.close()
        }catch (_ :Exception){

        }finally {
            socket = null
        }
        reconnect()
    }

    private fun reconnect(){
        Log.d(TAG, "reconnect")

        if (reconnect.get()){
            reconnectInterval = if (reconnectInterval < 10000) reconnectInterval + 2000 else reconnectInterval
            Timer(true).schedule(object :TimerTask(){
                override fun run() {
                    if (reconnect.get()){
                        connect()
                    }
                }
            }, reconnectInterval)

        }
    }


    fun disconnect() {
        reconnect.set(false)
        resolveResource()
    }
}