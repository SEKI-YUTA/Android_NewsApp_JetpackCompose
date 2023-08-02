package com.example.newsapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse_API(
    val articles: List<Article_API>,
    val status: String,
    val totalResults: Int
)
