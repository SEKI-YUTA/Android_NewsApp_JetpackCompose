package com.example.newsapp.data

import com.example.newsapp.data.model.NewsResponse_API
import com.example.newsapp.ui.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsRepository {
    suspend fun getTopNews(
        country: String,
        sortBy: String = "published",
        apiKey: String
    ): NewsResponse

    suspend fun getTopNewsByQuery(
        country: String,
        sortBy: String = "published",
        apiKey: String,
        query: String
    ): NewsResponse
    suspend fun getTopNewsByCategory(
        country: String,
        category: String,
        sortBy: String = "published",
        apiKey: String
    ): NewsResponse
}