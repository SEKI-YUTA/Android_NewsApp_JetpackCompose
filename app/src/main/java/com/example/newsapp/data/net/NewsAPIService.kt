package com.example.newsapp.data.net

import com.example.newsapp.data.model.NewsResponse_API
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/everything?q=tesla&from=2023-07-02&sortBy=publishedAt&apiKey=eb632193384348238d832abb8e6ae41b
//https://newsapi.org/v2/top-headlines?country=jp&apiKey=eb632193384348238d832abb8e6ae41b
interface NewsAPIService {
    @GET("top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String,
        @Query("sortBy") sortBy: String = "published",
        @Query("apiKey") apiKey: String
    ): NewsResponse_API
//    ): Response<NewsResponse_API>


}