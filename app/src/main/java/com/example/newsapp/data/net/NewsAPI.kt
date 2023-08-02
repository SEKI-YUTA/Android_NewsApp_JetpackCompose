package com.example.newsapp.data.net

import com.example.newsapp.other.ConstantValues
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit: Retrofit =
    Retrofit.Builder()
//        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(ConstantValues.BASE_URL)
        .build()

object NewsAPI {
    val apiService: NewsAPIService by lazy {
        retrofit.create(NewsAPIService::class.java)
    }
}