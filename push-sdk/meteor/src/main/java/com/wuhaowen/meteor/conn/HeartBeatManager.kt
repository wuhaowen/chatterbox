package com.wuhaowen.meteor.conn

import android.content.Context
import android.content.IntentFilter
import com.wuhaowen.meteor.common.AlarmAction
import com.wuhaowen.meteor.common.AlarmManagerUtils
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean


/**
 * 心跳管理
 *
 */
class HeartBeatManager(private val context: Context, private val pongTimeout:() -> Unit ,ping: () -> Unit) {

    private var interval: Long = 0L

    val needPong = AtomicBoolean(false)
    private var heartBeatPongTimer: Timer? = null



    private var broadcastReceiver = HeartbeatReceiver {
        ping()
        schedulePing()
    }


    init {
        context.registerReceiver(broadcastReceiver, IntentFilter().apply { addAction(AlarmAction) })
    }


    fun schedulePing() {
        heartBeatPongTimer?.cancel()
        heartBeatPongTimer = null
        needPong.set(false)
        interval = if (interval < 50000) interval + 5000 else interval
        AlarmManagerUtils.getInstance(context = context).start(interval)


    }

    fun waitPong(){
        heartBeatPongTimer?.cancel()
        heartBeatPongTimer = Timer(true).apply {
            needPong.set(true)
            schedule(object :TimerTask(){
                override fun run() {
                    if (needPong.get()){//心跳超时
                        pongTimeout()
                    }
                }

            }, 4000)
        }

    }

    fun stopPing() {
        heartBeatPongTimer?.cancel()
        AlarmManagerUtils.getInstance(context = context).cancel()
        context.unregisterReceiver(broadcastReceiver)
    }

}