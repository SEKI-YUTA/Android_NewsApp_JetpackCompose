package com.example.newsapp.ui.model


data class NewsResponse(
    val succeed: Boolean,
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
