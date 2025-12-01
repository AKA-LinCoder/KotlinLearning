package com.echo.kotlinlearning.fragmentbestpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.echo.kotlinlearning.R

class NewsContentFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_content_frag,container,false)
    }

    fun refresh(newsTitle: String, newsContent: String) {
        val contentLayout = view?.findViewById<View>(R.id.contentLayout) as ViewGroup
        contentLayout.visibility = View.VISIBLE
        val newsTitleView = view?.findViewById(R.id.newsTitle) as android.widget.TextView
        val newsContentView = view?.findViewById(R.id.newsContent) as android.widget.TextView
        newsTitleView.text = newsTitle
        newsContentView.text = newsContent
    }
}