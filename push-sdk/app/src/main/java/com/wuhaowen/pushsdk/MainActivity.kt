package com.wuhaowen.pushsdk

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wuhaowen.meteor.MeteorService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startService(Intent(this, MeteorService::class.java))
    }
}
