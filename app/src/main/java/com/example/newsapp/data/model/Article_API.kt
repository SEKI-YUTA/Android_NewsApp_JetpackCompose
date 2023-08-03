package com.example.newsapp.data.model

import androidx.annotation.Keep
import com.example.newsapp.ui.model.Article
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Article_API(
     val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source_API?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) {
    fun toUIModel(): Article {
        return Article(
            author = author,
            content = content,
            description = description,
            publishedAt = publishedAt,
            source = source?.toUIModel(),
            title = title,
            url = url,
            urlToImage = urlToImage
        )
    }
}
