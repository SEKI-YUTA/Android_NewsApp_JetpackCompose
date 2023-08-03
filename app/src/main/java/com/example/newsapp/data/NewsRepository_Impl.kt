package com.example.newsapp.data

import com.example.newsapp.data.net.NewsAPI
import com.example.newsapp.ui.model.NewsResponse

class NewsRepository_Impl: NewsRepository {
    override suspend fun getTopNews(country: String, sortBy: String, apiKey: String): NewsResponse {
        NewsAPI.apiService.getTopNews(country, sortBy, apiKey).let {
            return it.toUIModel()
        }
    }

    override suspend fun getTopNewsByCategory(
        country: String,
        category: String,
        sortBy: String,
        apiKey: String
    ): NewsResponse {
        NewsAPI.apiService.getTopNewsByCategory(country, category, sortBy, apiKey).let {
            return it.toUIModel()
        }
    }
}