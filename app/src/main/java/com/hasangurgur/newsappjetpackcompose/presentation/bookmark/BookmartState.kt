package com.hasangurgur.newsappjetpackcompose.presentation.bookmark

import com.hasangurgur.newsappjetpackcompose.domain.model.Article

data class BookmartState(
    val articles : List<Article> = emptyList()

)
