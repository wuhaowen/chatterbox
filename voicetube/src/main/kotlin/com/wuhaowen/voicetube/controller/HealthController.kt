package com.wuhaowen.voicetube.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/health")
class HealthController {

    @RequestMapping("")
    fun cheak() = "ok"
}