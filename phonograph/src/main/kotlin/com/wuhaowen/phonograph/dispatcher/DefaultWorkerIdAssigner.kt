package com.wuhaowen.phonograph.dispatcher


import com.wuhaowen.phonograph.dispatcher.uid.worker.WorkerIdAssigner
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@ConfigurationProperties(prefix = "phonograph")
class DefaultWorkerIdAssigner : WorkerIdAssigner {
    var machineId: Long = 0

    @Transactional
    override fun assignWorkerId() = machineId
}
