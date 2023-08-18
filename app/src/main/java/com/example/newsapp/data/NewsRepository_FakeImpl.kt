package com.example.newsapp.data

import com.example.newsapp.ui.model.NewsResponse
import kotlinx.coroutines.delay

// ニュースの情報をデモ用のデータから引っ張ってくる
class NewsRepository_FakeImpl: NewsRepository {
    override suspend fun getTopNews(country: String, sortBy: String, apiKey: String): NewsResponse {
        delay(100)
        return DemoData.demoResponse
    }

    override suspend fun getTopNewsByQuery(
        country: String,
        sortBy: String,
        apiKey: String,
        query: String
    ): NewsResponse {
        delay(100)
        return DemoData.demoResponse
    }

    override suspend fun getTopNewsByCategory(
        country: String,
        category: String,
        sortBy: String,
        apiKey: String
    ): NewsResponse {
        delay(100)
        return DemoData.demoResponse
    }
}