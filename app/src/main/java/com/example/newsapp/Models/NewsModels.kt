package com.example.newsapp.Models

data class NewsModels(
    val articles: List<Article>? = null,
    val status: String? = null,
    val totalResults: Int? = null
)