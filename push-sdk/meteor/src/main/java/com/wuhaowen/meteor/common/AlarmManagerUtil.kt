package com.wuhaowen.meteor.common

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.app.AlarmManager
import android.os.Build



const val AlarmAction = "com.wuhaowen.meteor.broadcast"
const val requestCode = 666
class AlarmManagerUtils private constructor(private val context: Context){
    private val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    private var pendingIntent :PendingIntent? = null

    companion object {
        private var instance: AlarmManagerUtils? = null

        fun getInstance(context:Context): AlarmManagerUtils {
            if (instance == null){
                synchronized(AlarmManagerUtils.javaClass){
                    if (instance == null){
                        instance = AlarmManagerUtils(context)
                    }
                }

            }
            return instance!!
        }
    }

    fun cancel(){
        pendingIntent?.let {
            am.cancel(it)
            pendingIntent = null
        }
    }


    @SuppressLint("NewApi")
    fun start(intervalSecond: Long) {
        pendingIntent?.let { am.cancel(it) }
        pendingIntent = PendingIntent.getBroadcast(context, requestCode, Intent(AlarmAction)
                .putExtra("intervalSecond", intervalSecond),0)

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> // 6.0及以上
                am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + intervalSecond, pendingIntent)
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> // 4.4及以上
                am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + intervalSecond,
                        pendingIntent)
            else -> am.set(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + intervalSecond , pendingIntent)
        }
    }


}
