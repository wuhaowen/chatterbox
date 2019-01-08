package com.wuhaowen.operator

import com.wuhaowen.operator.service.TweeterClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@SpringBootApplication
class OperatorApplication

fun main(args: Array<String>) {
    runApplication<OperatorApplication>(*args)
}

@Component
@ConfigurationProperties(prefix = "tweeter")
class TweeterServiceConf{

    var servicePath: String = ""

    @Bean fun tweeterClient(): TweeterClient = TweeterClient(servicePath)
}