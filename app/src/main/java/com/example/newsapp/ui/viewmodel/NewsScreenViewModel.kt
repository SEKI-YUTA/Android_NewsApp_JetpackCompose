package com.example.newsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.newsapp.data.NewsRepository
import com.example.newsapp.data.NewsRepository_Impl
import com.example.newsapp.data.SecretInfo
import com.example.newsapp.ui.model.NewsResponse
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
        "トップ" to "",
        "ビジネス" to "business",
        "芸能" to "entertainment",
        "健康" to "health",
        "科学" to "science",
        "スポーツ" to "sports",
        "テクノロジー" to "technology"
    )

    val _responseMap = MutableStateFlow(mapOf<String, NewsResponse?>(
        "トップ" to null,
        "ビジネス" to null,
        "芸能" to null,
        "健康" to null,
        "科学" to null,
        "スポーツ" to null,
        "テクノロジー" to null
    ))
    val responseMap = _responseMap.asStateFlow()

    private val _currentNewsResponse = MutableStateFlow<NewsResponse?>(null)
    val currentNewsResponse = _currentNewsResponse.asStateFlow()

    fun getNews(categoryKey: String ,category: String = ""){
        println("getNews called")
        _currentNewsResponse.value = null
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
//                val topNews = NewsAPI.apiService.getTopNews("jp", apiKey = SecretInfo.NEWS_API_KEY)
//                topNews.let {
//                    println("connection succeed status: ${it.status}")
//                    println("count: ${it.totalResults}")
//                }
                if(category == "") {
                    // カテゴリ指定なしでニュースを取得する場合
                    newsRepository.getTopNews("jp", apiKey = SecretInfo.NEWS_API_KEY).let {
                        println("connection succeed status: ${it.status}")
                        println("count: ${it.totalResults}")
                        println(it.articles[0].title)
                        _currentNewsResponse.value = it
                        addResponse(categoryKey, it)
                    }
                } else {
                    // カテゴリを指定してニュースを取得する場合
                    newsRepository.getTopNewsByCategory("jp", category, apiKey = SecretInfo.NEWS_API_KEY).let {
                        println("connection succeed status: ${it.status}")
                        println("count: ${it.totalResults}")
                        _currentNewsResponse.value = it
                        addResponse(categoryKey, it)
                    }
                }
            }
        }
    }

    fun checkResponse(key: String): Boolean{
        return _responseMap.value[key] != null
    }

    fun setCurrentResponse(key: String) {
        _currentNewsResponse.value = _responseMap.value[key]
    }

    fun addResponse(key: String, response: NewsResponse?){
        _responseMap.update {
            it.plus(key to response)
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