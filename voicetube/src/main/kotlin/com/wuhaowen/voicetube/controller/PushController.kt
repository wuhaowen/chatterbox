package com.wuhaowen.voicetube.controller

import com.wuhaowen.voicetube.pojo.Message
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/push")
class PushController {

    @PostMapping("/{userId}")
    fun push(@PathVariable userId:String, @RequestBody message: Message){

    }

    @PostMapping("/tag/{tag}")
    fun pushTag(@PathVariable tag:String, @RequestBody message: Message){

    }

}