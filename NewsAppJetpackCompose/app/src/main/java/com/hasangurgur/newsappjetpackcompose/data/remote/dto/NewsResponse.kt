package com.hasangurgur.newsappjetpackcompose.data.remote.dto

import com.hasangurgur.newsappjetpackcompose.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)