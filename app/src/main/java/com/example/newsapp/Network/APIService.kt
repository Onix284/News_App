package com.example.newsapp.Network

import com.example.newsapp.API_KEY
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.newsapp.Models.NewsModels

//My API Key : ba35166eb36d43c78a44175f69787677
//Website : q7

interface APIService {
    @GET("top-headlines")
    suspend fun getTOpHeadline(
        @Query("country") country : String,
        @Query("category") catagory : String,
        @Query("apiKey") apiKey : String = API_KEY
    ) : retrofit2.Response<NewsModels>
}