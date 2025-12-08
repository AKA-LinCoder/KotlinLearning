package com.echo.kotlinlearning.service

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.echo.kotlinlearning.R
import com.echo.kotlinlearning.databinding.ActivityNetWorkBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import kotlin.concurrent.thread

class NetWorkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNetWorkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNetWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl("https://www.baidu.com")
        binding.btnLoad.setOnClickListener {
            thread {
                try {
                    val client = OkHttpClient()
                    val request = Request.Builder()
                        .url("https://www.xiaomi.com").build()
                    val response = client.newCall(request).execute()
                    val responseData = response.body?.string()
                    if (responseData != null) {
                        runOnUiThread {
                            binding.webView.loadDataWithBaseURL(
                                null,
                                responseData,
                                "text/html",
                                "UTF-8",
                                null
                            )

                        }
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}