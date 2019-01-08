package com.wuhaowen.meteor.thread

import okio.BufferedSink

const val header = 0x66
const val version = 1

interface BaseTask :Task{
    var bytes: ByteArray
    var out:BufferedSink?
    var cb :(Throwable?) -> Unit
    var type:Int

//    override fun run() {
//
//        out?.run {
//            try {
//                Log.d(TAG, "发送数据")
//                val bodySize = bytes.size
//                timeout().deadline(4, TimeUnit.SECONDS)
//                writeByte(header)
//                writeByte(version)
//                writeByte(TYPE_JOIN)
//                writeShort(bodySize)
//                write(bytes)
//                flush()
//                timeout().clearDeadline()
//                cb(null)
//            } catch (e: Exception) {
//                Log.e(TAG, "发送数据", e)
//
//                cb(e)
//            }
//        }
//
//
//
//    }
}