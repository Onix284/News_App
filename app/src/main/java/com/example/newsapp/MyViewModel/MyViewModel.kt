package com.example.newsapp.MyViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Models.NewsModels
import com.example.newsapp.Repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MyViewModel : ViewModel() {

    val response = mutableStateOf<NewsModels?>(null)
    val repo = Repo()

    init {
        fetchNews()
    }

    fun fetchNews(){
        viewModelScope.launch(Dispatchers.IO) {
            val data =
                repo.NewsProvide(
                    country = "us",
                    category = "business"
            )
            response.value = data.body()

            Log.d("My_Tag", "fetchNews: ${data}")

        }
    }

}