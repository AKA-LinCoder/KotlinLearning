package com.echo.kotlinlearning.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    inner  class DownloadBinder : Binder() {
        fun startDownload() {
            Log.d("MyService", "startDownload executed")
        }
        fun getProgress(): Int {
            Log.d("MyService", "getProgress executed")
            return 0
        }
    }


    private val mBinder = DownloadBinder()

    override fun onCreate() {
        //service第一次创建的时候调用
        super.onCreate()
        Log.d("MyService", "onCreate executed")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //每次启动service都会调用
        Log.d("MyService", "onStartCommand executed")
        return super.onStartCommand(intent, flags, startId)
    }



    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService", "onDestroy executed")
    }



}