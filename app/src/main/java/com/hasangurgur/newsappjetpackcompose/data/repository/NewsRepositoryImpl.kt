package com.hasangurgur.newsappjetpackcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hasangurgur.newsappjetpackcompose.data.local.NewsDao
import com.hasangurgur.newsappjetpackcompose.data.remote.NewsApi
import com.hasangurgur.newsappjetpackcompose.data.remote.NewsPagingSource
import com.hasangurgur.newsappjetpackcompose.data.remote.SearchNewsPagingSource
import com.hasangurgur.newsappjetpackcompose.domain.model.Article
import com.hasangurgur.newsappjetpackcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDap: NewsDao
) : NewsRepository {

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow

    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi = newsApi,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDap.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDap.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDap.getArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
        return newsDap.getArticle(url)
    }
}