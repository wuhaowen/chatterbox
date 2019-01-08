package com.wuhaowen.phonograph.dispatcher.mq

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.QueueBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component


/**
 * @program: phonograph
 * @description: MQ配置
 * @author: wuhaowen
 * @create: 2018-10-2018/10/19
 **/


@Component
@ConfigurationProperties(prefix = "phonograph")
class RabbitMqConf {
    var mqExchangeName: String =""
    var mqQueueName :String = "message"
    var routerKey: String = "com.wuhaowen.push"

    @Bean
    fun defaultExchange(): DirectExchange = DirectExchange(mqExchangeName)

    @Bean
    fun defaultQueue() = QueueBuilder.durable(mqQueueName).build()


    @Bean
    fun binding(queue: Queue, exchange: DirectExchange) = BindingBuilder
            .bind(queue).to(exchange).with(routerKey)

}