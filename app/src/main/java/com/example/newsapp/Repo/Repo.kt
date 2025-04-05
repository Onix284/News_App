package com.example.newsapp.Repo

import com.example.newsapp.Models.NewsModels
import com.example.newsapp.Network.RetrofitClient
import retrofit2.Response

class Repo {
    suspend fun NewsProvide(country : String, category : String) : Response<NewsModels>{
        return RetrofitClient.apiProvider().getTOpHeadline(
            country = country,
            catagory = category
        )
    }
}