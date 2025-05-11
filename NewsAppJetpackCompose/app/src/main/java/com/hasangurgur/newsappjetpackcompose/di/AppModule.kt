package com.hasangurgur.newsappjetpackcompose.di

import android.app.Application
import com.hasangurgur.newsappjetpackcompose.data.manager.LocalUserManagerImpl
import com.hasangurgur.newsappjetpackcompose.data.remote.NewsApi
import com.hasangurgur.newsappjetpackcompose.data.repository.NewsRepositoryImpl
import com.hasangurgur.newsappjetpackcompose.domain.manager.LocalUserManager
import com.hasangurgur.newsappjetpackcompose.domain.repository.NewsRepository
import com.hasangurgur.newsappjetpackcompose.domain.usecases.app_entry.AppEntryUseCases
import com.hasangurgur.newsappjetpackcompose.domain.usecases.app_entry.ReadAppEntry
import com.hasangurgur.newsappjetpackcompose.domain.usecases.app_entry.SaveAppEntry
import com.hasangurgur.newsappjetpackcompose.domain.usecases.news.GetNews
import com.hasangurgur.newsappjetpackcompose.domain.usecases.news.NewsUseCases
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
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository)
        )
}



}