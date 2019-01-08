package com.wuhaowen.meteor.conn

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class HeartbeatReceiver(private val block: (Long)->Unit) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        var intervalSecond = intent.getLongExtra("intervalSecond",5)
        block(intervalSecond)
//        intervalSecond = if (intervalSecond <= 115) intervalSecond + 5 else intervalSecond
//        AlarmManagerUtils.getInstance(context).startInReceiver(intervalSecond)
    }
}
