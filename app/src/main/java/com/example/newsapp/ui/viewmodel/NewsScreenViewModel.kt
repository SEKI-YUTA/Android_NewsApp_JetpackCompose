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
                topNews.let {
                    println("connection succeed status: ${it.status}")
                    println("count: ${it.totalResults}")
                }
            }
        }
    }
}