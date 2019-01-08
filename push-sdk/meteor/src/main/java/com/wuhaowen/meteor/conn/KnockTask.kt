package com.wuhaowen.meteor.conn

import com.wuhaowen.meteor.proto.TweetOuterClass
import com.wuhaowen.meteor.thread.BaseTask
import com.wuhaowen.meteor.thread.TaskPriority
import okio.BufferedSink

class KnockTask(override var out: BufferedSink?,
                lastSeq:Long,
                userId: String,
                tag:String,
                appkey:String,
                override  var cb: (Throwable?) -> Unit)
    : BaseTask {
    override var type = TYPE_JOIN
    override var bytes: ByteArray = TweetOuterClass.Knock.newBuilder()
            .setAppKey(appkey)
            .setUserId(userId)
            .setTag(tag)
            .setLastSeq(lastSeq).build().toByteArray()
    override var priority: TaskPriority = TaskPriority.HIGH

}