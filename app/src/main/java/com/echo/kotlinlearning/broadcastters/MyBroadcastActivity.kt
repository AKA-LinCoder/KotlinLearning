package com.echo.kotlinlearning.broadcastters

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.echo.kotlinlearning.R
import com.echo.kotlinlearning.databinding.ActivityMyBroadcastBinding

class MyBroadcastActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyBroadcastBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMyBroadcastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.broadcastbtn.setOnClickListener {
            val intent = Intent("com.echo.kotlinlearning.broadcastters.MY_BROADCAST")
            //为了将隐式广播变成显示广播
            intent.setPackage(packageName)
            //发送标准广播
            sendBroadcast(intent)
        }
        binding.broadcastorderbtn.setOnClickListener {

            val intent = Intent("com.echo.kotlinlearning.broadcastters.MY_BROADCAST")
            //为了将隐式广播变成显示广播
            intent.setPackage(packageName)
            //发送有序广播
            sendOrderedBroadcast(intent,null)
        }



    }
}