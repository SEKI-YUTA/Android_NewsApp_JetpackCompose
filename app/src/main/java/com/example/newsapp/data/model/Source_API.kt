package com.example.newsapp.data.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Source_API(
    val id: String?,
    val name: String
)
