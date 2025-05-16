package com.hasangurgur.newsappjetpackcompose.domain.usecases.news

import com.hasangurgur.newsappjetpackcompose.data.local.NewsDao
import com.hasangurgur.newsappjetpackcompose.domain.model.Article
import com.hasangurgur.newsappjetpackcompose.domain.repository.NewsRepository

class DeleteArticle (
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article)
    }
}