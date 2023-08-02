package com.example.newsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.SecretInfo
import com.example.newsapp.data.net.NewsAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsScreenViewModel: ViewModel() {
    val tabsMap = mapOf<String, String>(
        "トップ" to "headline",
        "芸能" to "entertainment",
        "ビジネス" to "business",
    )


    fun getNews(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val topNews = NewsAPI.apiService.getTopNews("jp", apiKey = SecretInfo.NEWS_API_KEY)
                if(topNews != null) {
                    println("connection succeed status: ${topNews.status}")
                    println("count: ${topNews.totalResults}")
                } else {
                    println("connection failed")

                }
//                if(topNews.isSuccessful)  {
//                println("connection succeed")
//                    topNews.body().let {
//                        println(it.toString())
//                        println("article count: ${it?.articles?.size}")
//                    }
//                } else {
//                    println("connection failed")
//                }
            }
        }
    }
}