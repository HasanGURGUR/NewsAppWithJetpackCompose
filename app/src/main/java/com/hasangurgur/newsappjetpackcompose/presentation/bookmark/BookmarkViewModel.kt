package com.hasangurgur.newsappjetpackcompose.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasangurgur.newsappjetpackcompose.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {


    private val _state = mutableStateOf(BookmartState())
    val state: State<BookmartState> = _state


    init {
        getArticles()
    }

    private fun getArticles() {
        newsUseCases.selectArticles().onEach {
            _state.value = state.value.copy(articles = it.asReversed())
        }.launchIn(viewModelScope)
    }
}