package com.example.newsapp.data.model

import androidx.annotation.Keep
import com.example.newsapp.ui.model.NewsResponse
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class NewsResponse_API(
    val articles: List<Article_API>,
    val status: String,
    val totalResults: Int
) {
    fun toUIModel(): NewsResponse {
        return NewsResponse(
            succeed = true,
            articles = articles.map { it.toUIModel() },
            status = status,
            totalResults = totalResults
        )
    }
}
