package com.wuhaowen.meteor

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.wuhaowen.meteor.conn.Channel
import android.content.pm.PackageManager
import android.content.ComponentName
import java.net.HttpURLConnection
import android.R.attr.password
import android.util.Log
import com.wuhaowen.meteor.common.UserInfo
import com.wuhaowen.meteor.conn.TAG
import okio.buffer
import okio.source
import org.json.JSONObject
import java.net.URL
import java.net.URLEncoder


class MeteorService: Service() {
    var channel: Channel? = null


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        channel?.disconnect()
        //channel = Channel().apply { connect() }
//        val userId = intent.getStringExtra("userId")
//        val cn = ComponentName(this, MeteorService::class.java)
//        val info = this.packageManager.getServiceInfo(cn, PackageManager.GET_META_DATA)
//        val appkey = info.metaData.getString("meteor_appkey")
//        val door_path = info.metaData.getString("meteor_door")
        //getPushServerInfo(door_path, appkey)

        //test
        Thread {
            val userInfo = UserInfo("test123", "", "appkey123")
            Log.d(TAG, "启动连接")
            channel = Channel("192.168.100.66", 7777, userInfo, this).apply {
                connect()
            }
        }.apply {
            isDaemon = true
            start()
        }
        return super.onStartCommand(intent, flags, startId)

    }

    private fun getPushServerInfo(doorPath: String, appkey: String, offlineMessage: Boolean){
        try {
            val url = URL("$doorPath?appkey=${URLEncoder.encode(appkey)}")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.connectTimeout = 8 * 1000
            conn.useCaches = false
            conn.doInput = true
            conn.doOutput = true
            conn.readTimeout = 8 * 1000
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8")
            conn.addRequestProperty("Connection", "Keep-Alive")
            conn.setRequestProperty("Charset", "UTF-8")
            conn.setRequestProperty("accept","application/json")
            val request = JSONObject()
            request.put("offlineMessage", offlineMessage)
            conn.outputStream.apply {
                write(request.toString().toByteArray())
                flush()
                close()
            }

            val code = conn.responseCode
            if (code == 200) {
                val input = conn.inputStream.source().buffer()
                val json = JSONObject(input.readUtf8())
                val servers = json.getJSONArray("servers")
                if (offlineMessage){
                    val offlineMessages = json.getJSONArray("messages")
                }




            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
}