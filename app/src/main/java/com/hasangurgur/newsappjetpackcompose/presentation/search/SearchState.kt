package com.hasangurgur.newsappjetpackcompose.presentation.search

import androidx.paging.PagingData
import com.hasangurgur.newsappjetpackcompose.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null,
) {
}