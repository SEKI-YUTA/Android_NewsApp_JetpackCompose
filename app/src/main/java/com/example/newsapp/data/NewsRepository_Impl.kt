package com.example.newsapp.data

import com.example.newsapp.data.model.NewsResponse_API
import com.example.newsapp.data.net.NewsAPI
import com.example.newsapp.ui.model.NewsResponse
import retrofit2.HttpException

class NewsRepository_Impl: NewsRepository {
    override suspend fun getTopNews(country: String, sortBy: String, apiKey: String): NewsResponse {
        try {
            NewsAPI.apiService.getTopNews(country, sortBy, apiKey).let {
                return it.toUIModel()
            }
        } catch (e: HttpException) {
            return NewsResponse(
                succeed = false,
                articles = listOf(),
                status = e.message(),
                totalResults = 0
            )
        }
    }

    override suspend fun getTopNewsByQuery(
        country: String,
        sortBy: String,
        apiKey: String,
        query: String
    ): NewsResponse {
        try {
            NewsAPI.apiService.getTopNewsByQuery(country, sortBy, apiKey, query).let {
                return it.toUIModel()
            }
        } catch(e: HttpException) {
            return NewsResponse(
                succeed = false,
                articles = listOf(),
                status = e.message(),
                totalResults = 0
            )
        }
    }

    override suspend fun getTopNewsByCategory(
        country: String,
        category: String,
        sortBy: String,
        apiKey: String
    ): NewsResponse {
        try {
            NewsAPI.apiService.getTopNewsByCategory(country, category, sortBy, apiKey).let {
                return it.toUIModel()
            }
        } catch(e: HttpException) {
            return NewsResponse(
                succeed = false,
                articles = listOf(),
                status = e.message(),
                totalResults = 0
            )
        }
    }
}