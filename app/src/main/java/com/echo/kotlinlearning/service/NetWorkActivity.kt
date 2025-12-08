package com.echo.kotlinlearning.service

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.echo.kotlinlearning.R
import com.echo.kotlinlearning.databinding.ActivityNetWorkBinding
import kotlinx.coroutines.launch
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
        binding.btnLoadXml.setOnClickListener{
            lifecycleScope.launch {
                val xmlUrl = "https://xxx.com/api/books.xml" // 替换为实际接口
                val books = parseXmlFromNetwork(this@NetWorkActivity,"book.xml")
                // 主线程更新UI
                Log.d("XML解析", "解析结果：$books")
                // 示例输出：[Book(id=1, title=Android开发艺术探索, author=任玉刚), ...]
            }
            lifecycleScope.launch {
//                val xmlUrl = "https://xxx.com/api/books.xml" // 替换为实际接口
//                val books = parseXmlWithSimpleXml(this@NetWorkActivity,"file.xml")
//                // 主线程更新UI
//                Log.d("XML解析", "解析结果：$books")
                // 示例输出：[Book(id=1, title=Android开发艺术探索, author=任玉刚), ...]
            }
        }
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