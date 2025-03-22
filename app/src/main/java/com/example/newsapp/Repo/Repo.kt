package com.example.newsapp.Repo

import com.example.newsapp.Models.NewsModels
import com.example.newsapp.Network.RetrofitClient
import okhttp3.Response

class Repo {

    suspend fun NewsProvide(
         country : String,
         category : String,
    ) : retrofit2.Response<NewsModels>{
        return RetrofitClient.apiProvider().getTOpHeadline(
            country = country,
            catagory = category
        )
    }
}