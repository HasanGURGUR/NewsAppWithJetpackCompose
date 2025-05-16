package com.hasangurgur.newsappjetpackcompose.domain.usecases.news

import com.hasangurgur.newsappjetpackcompose.data.local.NewsDao
import com.hasangurgur.newsappjetpackcompose.domain.model.Article
import com.hasangurgur.newsappjetpackcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles (
    private val newsRepository: NewsRepository
) {

     operator fun invoke() : Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}