package com.wuhaowen.meteor.conn

import com.wuhaowen.meteor.proto.TweetOuterClass
import com.wuhaowen.meteor.thread.BaseTask
import com.wuhaowen.meteor.thread.TaskPriority
import okio.BufferedSink

class MessageReplyTask(override var out: BufferedSink?, lastSeq: Long,
                       override  var cb: (Throwable?) -> Unit)
    : BaseTask {
    override var type = TYPE_MESSAGE_REPLY
    override var bytes: ByteArray = TweetOuterClass.MessageReply.newBuilder().setLastSeq(lastSeq).build().toByteArray()
    override var priority: TaskPriority = TaskPriority.MEDIUM

}