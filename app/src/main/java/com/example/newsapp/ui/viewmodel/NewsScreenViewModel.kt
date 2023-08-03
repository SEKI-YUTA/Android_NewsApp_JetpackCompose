package com.example.newsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.newsapp.data.NewsRepository
import com.example.newsapp.data.NewsRepository_Impl
import com.example.newsapp.data.SecretInfo
import com.example.newsapp.data.net.NewsAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsScreenViewModel(
    private val newsRepository: NewsRepository
): ViewModel() {
    val tabsMap = mapOf<String, String>(
        "トップ" to "headline",
        "芸能" to "entertainment",
        "ビジネス" to "business",
    )


    fun getNews(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
//                val topNews = NewsAPI.apiService.getTopNews("jp", apiKey = SecretInfo.NEWS_API_KEY)
//                topNews.let {
//                    println("connection succeed status: ${it.status}")
//                    println("count: ${it.totalResults}")
//                }
                newsRepository.getTopNews("jp", apiKey = SecretInfo.NEWS_API_KEY).let {
                    println("connection succeed status: ${it.status}")
                    println("count: ${it.totalResults}")
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                NewsScreenViewModel(
                    newsRepository = NewsRepository_Impl()
                )
            }
        }
    }
}