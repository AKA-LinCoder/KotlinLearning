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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.broadcastbtn.setOnClickListener {
            val intent = Intent("com.echo.kotlinlearning.broadcastters.MY_BROADCAST")
            //为了将隐式广播变成显示广播
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }
    }
}