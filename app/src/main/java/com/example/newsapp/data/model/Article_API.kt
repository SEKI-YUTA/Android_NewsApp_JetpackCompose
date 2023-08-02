package com.example.newsapp.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class Article_API(
     val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source_API,
    val title: String,
    val url: String,
    val urlToImage: String
)
