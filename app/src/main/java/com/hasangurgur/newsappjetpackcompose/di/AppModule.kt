package com.hasangurgur.newsappjetpackcompose.di

import android.app.Application
import androidx.room.Room
import com.hasangurgur.newsappjetpackcompose.data.local.NewsDao
import com.hasangurgur.newsappjetpackcompose.data.local.NewsDatabase
import com.hasangurgur.newsappjetpackcompose.data.local.NewsTypeConvertor
import com.hasangurgur.newsappjetpackcompose.data.manager.LocalUserManagerImpl
import com.hasangurgur.newsappjetpackcompose.data.remote.NewsApi
import com.hasangurgur.newsappjetpackcompose.data.repository.NewsRepositoryImpl
import com.hasangurgur.newsappjetpackcompose.domain.manager.LocalUserManager
import com.hasangurgur.newsappjetpackcompose.domain.repository.NewsRepository
import com.hasangurgur.newsappjetpackcompose.domain.usecases.app_entry.AppEntryUseCases
import com.hasangurgur.newsappjetpackcompose.domain.usecases.app_entry.ReadAppEntry
import com.hasangurgur.newsappjetpackcompose.domain.usecases.app_entry.SaveAppEntry
import com.hasangurgur.newsappjetpackcompose.domain.usecases.news.DeleteArticle
import com.hasangurgur.newsappjetpackcompose.domain.usecases.news.GetNews
import com.hasangurgur.newsappjetpackcompose.domain.usecases.news.NewsUseCases
import com.hasangurgur.newsappjetpackcompose.domain.usecases.news.SearchNews
import com.hasangurgur.newsappjetpackcompose.domain.usecases.news.SelectArticle
import com.hasangurgur.newsappjetpackcompose.domain.usecases.news.SelectArticles
import com.hasangurgur.newsappjetpackcompose.domain.usecases.news.UpsertArticle
import com.hasangurgur.newsappjetpackcompose.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readyAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )


    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao

        ): NewsRepository = NewsRepositoryImpl(newsApi,newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository,newsDao : NewsDao): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository = newsRepository),
            deleteArticle = DeleteArticle(newsRepository = newsRepository),
            selectArticles = SelectArticles(newsRepository = newsRepository),
            selectArticle = SelectArticle(newsRepository = newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = Constants.NEWS_DATABASE_NAME
        ).addTypeConverter(
            NewsTypeConvertor()
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao


}