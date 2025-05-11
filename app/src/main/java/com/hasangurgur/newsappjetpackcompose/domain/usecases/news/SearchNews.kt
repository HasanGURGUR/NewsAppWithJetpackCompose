package com.hasangurgur.newsappjetpackcompose.domain.usecases.news

import androidx.paging.PagingData
import com.hasangurgur.newsappjetpackcompose.domain.model.Article
import com.hasangurgur.newsappjetpackcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(sources = sources, searchQuery = searchQuery)
    }
}