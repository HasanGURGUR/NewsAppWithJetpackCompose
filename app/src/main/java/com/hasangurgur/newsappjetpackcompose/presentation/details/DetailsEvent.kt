package com.hasangurgur.newsappjetpackcompose.presentation.details

import com.hasangurgur.newsappjetpackcompose.domain.model.Article
import com.hasangurgur.newsappjetpackcompose.domain.usecases.news.UpsertArticle

sealed class DetailsEvent {
    data class UpsertDeleteArticle (val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
    
}