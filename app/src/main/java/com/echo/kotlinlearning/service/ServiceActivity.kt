package com.echo.kotlinlearning.service

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.echo.kotlinlearning.R
import com.echo.kotlinlearning.databinding.ActivityServiceBinding

class ServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServiceBinding
    private lateinit var downloadBinder: MyService.DownloadBinder

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startService.setOnClickListener {

            val intent = Intent(this, MyService::class.java) //启动服务
            startService(intent)
        }
        binding.stopService.setOnClickListener {
            val intent = Intent(this, MyService::class.java) //启动服务
            stopService(intent)
        }
        binding.bindService.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            bindService(intent, connection, BIND_AUTO_CREATE)
        }
        binding.unbindService.setOnClickListener {
            unbindService(connection)
        }

    }
}