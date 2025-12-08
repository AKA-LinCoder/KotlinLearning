package com.echo.kotlinlearning.service

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.simpleframework.xml.core.Persister
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

private val okHttpClient = OkHttpClient()

suspend fun parseXmlFromNetwork(context: Context,xmlPath: String): List<Book> = withContext(Dispatchers.IO) {
    val bookList = mutableListOf<Book>()
    var currentId = 0
    var currentTitle = ""
    var currentAuthor = ""
    var currentTag:String? = null


//    val request = Request.Builder().url(xmlPath).build()
//    val response = okHttpClient.newCall(request).execute()
//    val inputStream = response.body?.byteStream() ?: return@withContext emptyList<Book>()
    val inputStream = context.assets.open(xmlPath)

    val factory = XmlPullParserFactory.newInstance()
    factory.isNamespaceAware = false
    val parser = factory.newPullParser()
    parser.setInput(inputStream,"UTF-8")

    var eventType = parser.eventType
    while (eventType != XmlPullParser.END_DOCUMENT) {
        when (eventType) {
            // 开始标签（<book>、<id>等）
            XmlPullParser.START_TAG -> {
                currentTag = parser.name
                // 重置临时变量（新的<book>节点开始）
                if ("book" == currentTag) {
                    currentId = 0
                    currentTitle = ""
                    currentAuthor = ""
                }
            }
            // 标签内的文本内容
            XmlPullParser.TEXT -> {
                val text = parser.text.trim()
                when (currentTag) {
                    "id" -> currentId = text.toIntOrNull() ?: 0
                    "title" -> currentTitle = text
                    "author" -> currentAuthor = text
                }
            }
            // 结束标签（</book>）
            XmlPullParser.END_TAG -> {
                if ("book" == parser.name) {
                    // 封装Book对象并添加到列表
                    bookList.add(Book(currentId, currentTitle, currentAuthor))
                }
                currentTag = null // 重置当前标签
            }

        }
        eventType = parser.next() // 解析下一个事件
    }

    inputStream.close()
    return@withContext bookList
}

suspend fun parseXmlWithSimpleXml(context: Context,xmlUrl: String): List<File> = withContext(Dispatchers.IO) {
    // 1. OkHttp获取XML流
//    val request = Request.Builder().url(xmlUrl).build()
//    val response = okHttpClient.newCall(request).execute()
//    val inputStream = response.body?.byteStream() ?: return@withContext emptyList()
    val inputStream = context.assets.open(xmlUrl)

    // 2. Simple XML解析
    val serializer = Persister()
    val bookList = serializer.read(FileList::class.java, inputStream)
    inputStream.close()
    return@withContext bookList.files
}