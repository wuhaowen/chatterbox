package com.wuhaowen.meteor.conn

import com.wuhaowen.meteor.proto.TweetOuterClass
import com.wuhaowen.meteor.thread.BaseTask
import com.wuhaowen.meteor.thread.TaskPriority
import okio.BufferedSink

class PingTask(override var out: BufferedSink?,
               override var cb: (Throwable?) -> Unit) : BaseTask {
    override var type = TYPE_PING
    override var bytes: ByteArray = TweetOuterClass.Tweet.newBuilder().setEcho(1).build().toByteArray()
    override var priority: TaskPriority = TaskPriority.LOW


}