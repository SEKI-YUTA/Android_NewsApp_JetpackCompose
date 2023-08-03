package com.example.newsapp.data

import com.example.newsapp.ui.model.NewsResponse


interface NewsRepository {
    suspend fun getTopNews(
        country: String,
        sortBy: String = "published",
        apiKey: String
    ): NewsResponse
}