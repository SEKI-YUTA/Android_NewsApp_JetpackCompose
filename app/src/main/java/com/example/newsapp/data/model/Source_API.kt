package com.example.newsapp.data.model

import androidx.annotation.Keep
import com.example.newsapp.ui.model.Source
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Source_API(
    val id: String?,
    val name: String
) {
    fun toUIModel(): Source {
        return Source(
            id = id,
            name = name
        )
    }
}
