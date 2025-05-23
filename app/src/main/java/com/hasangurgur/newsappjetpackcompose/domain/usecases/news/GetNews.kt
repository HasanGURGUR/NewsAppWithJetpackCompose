package com.hasangurgur.newsappjetpackcompose.domain.usecases.news

import androidx.paging.PagingData
import com.hasangurgur.newsappjetpackcompose.domain.model.Article
import com.hasangurgur.newsappjetpackcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository : NewsRepository
) {
    operator fun invoke(sources : List<String>) : Flow<PagingData<Article>>{
        return newsRepository.getNews(sources = sources)
    }
}