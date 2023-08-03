package com.example.newsapp.data.net

import com.example.newsapp.other.ConstantValues
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mediaType = "application/json".toMediaType()
val retrofit: Retrofit =
    Retrofit.Builder()
        .addConverterFactory(Json{coerceInputValues = true}.asConverterFactory(mediaType))
        .baseUrl(ConstantValues.BASE_URL)
        .build()

object NewsAPI {
    val apiService: NewsAPIService by lazy {
        retrofit.create(NewsAPIService::class.java)
    }
}