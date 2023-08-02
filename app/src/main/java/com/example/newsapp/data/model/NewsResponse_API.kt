package com.example.newsapp.data.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class NewsResponse_API(
    val articles: List<Article_API>,
    val status: String,
    val totalResults: Int
)
