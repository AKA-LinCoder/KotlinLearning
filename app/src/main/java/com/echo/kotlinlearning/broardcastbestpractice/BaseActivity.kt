package com.echo.kotlinlearning.broardcastbestpractice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

open class BaseActivity: AppCompatActivity() {

    lateinit var forceOffLineReceiver:ForceOffLineReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("LOGOUT", "onCreate")
        ActivityCollector.addActivity(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("LOGOUT", "onDestroy")
        ActivityCollector.removeActivity(this)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onResume() {
        super.onResume()
        Log.e("LOGOUT", "onResume")
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.echo.kotlinlearning.broadcastters.MY_LOGOUT")
        forceOffLineReceiver = ForceOffLineReceiver()
        registerReceiver(forceOffLineReceiver, intentFilter, RECEIVER_NOT_EXPORTED)

    }


    override fun onPause() {
        super.onPause()
        Log.e("LOGOUT","onPause")
        unregisterReceiver(forceOffLineReceiver)
    }

    inner class ForceOffLineReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.e("LOGOUT","强制下线")
            AlertDialog.Builder(context!!)
                .setTitle("警告")
                .setMessage("您的账号在另一台设备上登录，请确认是否为本人操作")
                .setCancelable(false)
                .setPositiveButton("确定") { dialog, which ->
                ActivityCollector.finishAll() // 销毁所有活动
                    val i = Intent(context, LoginActivity::class.java)
                    context.startActivity(i)
                }.show()
        }

    }
}