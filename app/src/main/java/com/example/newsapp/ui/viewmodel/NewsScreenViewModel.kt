package com.example.newsapp.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.newsapp.NewsApplication
import com.example.newsapp.R
import com.example.newsapp.data.NewsRepository
import com.example.newsapp.data.NewsRepository_Impl
import com.example.newsapp.data.SecretInfo
import com.example.newsapp.other.AppUtil
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
    private val context: Context,
    private val newsRepository: NewsRepository
): ViewModel() {
    val topStr = context.getString(R.string.top_news)
    val businessStr = context.getString(R.string.business_news)
    val entertainmentStr = context.getString(R.string.entertainment_news)
    val healthStr = context.getString(R.string.health_news)
    val scienceStr = context.getString(R.string.science_news)
    val sportsStr = context.getString(R.string.sports_news)
    val technologyStr = context.getString(R.string.technology_news)

    val tabsMap = mapOf<String, String>(
        topStr to "",
        businessStr to "business",
        entertainmentStr to "entertainment",
        healthStr to "health",
        scienceStr to "science",
        sportsStr to "sports",
        technologyStr to "technology"
    )

    private val searchResultMap = mutableMapOf<String, NewsResponse>()

    private val _responseMap = MutableStateFlow(mapOf<String, NewsResponse?>(
        topStr to null,
        businessStr to null,
        entertainmentStr to null,
        healthStr to null,
        scienceStr to null,
        sportsStr to null,
        technologyStr to null
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

    private val _isNetworkConnected = MutableStateFlow(true)
    val isNetworkConnected = _isNetworkConnected.asStateFlow()

    private val _currentScreen = MutableStateFlow(Screen.NewsScreen)
    val currentScreen = _currentScreen.asStateFlow()

    init {
        AppUtil.checkNetworkConnection(context, _isNetworkConnected)
    }

    fun searchNews(query: String){
        if(searchResultMap[query] != null) {
            _currentSearchResult.value = searchResultMap[query]
            return
        } else if(searchResultMap[query] == null && _isNetworkConnected.value.not()) {
            Toast.makeText(context, context.getString(R.string.network_not_connected), Toast.LENGTH_SHORT).show()
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
        if(_isNetworkConnected.value.not()) {
            Toast.makeText(context, context.getString(R.string.network_not_connected), Toast.LENGTH_SHORT).show()
            return
        }
        println("getNews called")
        _currentNewsResponse.value = null
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
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
                val context = (this[APPLICATION_KEY] as NewsApplication).container.context
                NewsScreenViewModel(
                    newsRepository = NewsRepository_Impl(),
                    context = context
                    // デモ用のフェイクリポジトリ
//                newsRepository = DemoRepository_Impl()
                )
            }
        }
    }
}