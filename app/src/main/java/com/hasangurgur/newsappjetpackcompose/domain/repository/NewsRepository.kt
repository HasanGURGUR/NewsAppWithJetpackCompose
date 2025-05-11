package com.hasangurgur.newsappjetpackcompose.domain.repository

import androidx.paging.PagingData
import com.hasangurgur.newsappjetpackcompose.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>
}