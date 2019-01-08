package com.wuhaowen.meteor.thread

import android.util.Log
import com.wuhaowen.meteor.conn.TAG
import com.wuhaowen.meteor.conn.TYPE_JOIN
import java.util.concurrent.*


object TaskWriteThreadPool {


    private val taskQueue = PriorityBlockingQueue<BaseTask>(500)
    private var executorService: ExecutorService? = null
    fun init() {
        executorService = Executors.newSingleThreadExecutor {
            Thread(it).apply { isDaemon = true }
        }.apply {
            submit {
                while (!Thread.interrupted()) {
                    try {
                        taskQueue.take().run {
                            try {
                                Log.d(TAG, "发送数据")
                                out?.run {
                                    val bodySize = bytes.size
                                    timeout().deadline(4, TimeUnit.SECONDS)
                                    writeByte(header)
                                    writeByte(version)
                                    writeByte(type)
                                    writeShort(bodySize)
                                    write(bytes)
                                    flush()
                                    timeout().clearDeadline()
                                    cb(null)
                                }
                            } catch (e: Exception) {
                                Log.e(TAG, "发送数据", e)

                                cb(e)
                            }

                        }
                    }catch (e: InterruptedException){
                        Thread.currentThread().interrupt()
                        Log.e(TAG,"发送线程异常" ,e)

                    }catch (ex: Exception){
                        Log.e(TAG,"发送线程异常" ,ex)

                    }

                }

            }
        }

    }

    fun submit(task: BaseTask) {
        taskQueue.offer(task)

    }

    fun shutdown() {
        taskQueue.clear()
        executorService?.shutdown()
        executorService = null
    }
}