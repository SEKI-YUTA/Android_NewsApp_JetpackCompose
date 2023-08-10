package com.example.newsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.newsapp.data.DemoRepository_Impl
import com.example.newsapp.data.NewsRepository
import com.example.newsapp.data.NewsRepository_Impl
import com.example.newsapp.data.SecretInfo
import com.example.newsapp.ui.Screen
import com.example.newsapp.ui.model.Article
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

    val searchResultMap = mutableMapOf<String, NewsResponse>()

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

    private val _currentArticle = MutableStateFlow<Article?>(null)
    val currentArticle = _currentArticle.asStateFlow()

    private val _currentSearchResult = MutableStateFlow<NewsResponse?>(null)
    val currentSearchResult = _currentSearchResult.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _currentScreen = MutableStateFlow(Screen.NewsScreen)
    val currentScreen = _currentScreen.asStateFlow()

    fun searchNews(query: String){
        if(searchResultMap[query] != null) {
            _currentSearchResult.value = searchResultMap[query]
            return
        }
        _currentSearchResult.value = null
        _isSearching.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                newsRepository.getTopNewsByQuery("jp", apiKey = SecretInfo.NEWS_API_KEY, query = query).let {
                    println("connection succeed status: ${it.status}")
                    println("count: ${it.totalResults}")
                    _currentSearchResult.value = it
                    _isSearching.value = false
                    searchResultMap[query] = it
                }
            }
        }
    }

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

    fun setCurrentArticle(article: Article) {
        _currentArticle.value = article
    }

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                NewsScreenViewModel(
                    newsRepository = NewsRepository_Impl()
                    // デモ用のフェイクリポジトリ
//                newsRepository = DemoRepository_Impl()
                )
            }
        }
    }
}