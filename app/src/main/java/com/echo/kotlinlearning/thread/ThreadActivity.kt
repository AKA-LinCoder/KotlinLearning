package com.echo.kotlinlearning.thread

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.echo.kotlinlearning.R
import com.echo.kotlinlearning.databinding.ActivityThreadBinding
import kotlin.concurrent.thread

class ThreadActivity : AppCompatActivity() {

    val updateText = 1
    private lateinit var binding: ActivityThreadBinding
    val handle = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                updateText -> binding.tvThread.text = "Nice to meet you"
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnThread.setOnClickListener {
            thread {
                val msg = Message()
                msg.what = 1
                handle.sendMessage(msg)

            }
        }

    }
}