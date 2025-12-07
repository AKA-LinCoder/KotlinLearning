package com.echo.kotlinlearning.thread

import android.os.AsyncTask
//已经逐渐舍弃，使用kotlin协程来操作
class DownloadTask: AsyncTask<Unit,Int, Boolean>() {
    override fun doInBackground(vararg params: Unit?): Boolean? {
        TODO("Not yet implemented")
    }
}