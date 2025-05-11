package com.hasangurgur.newsappjetpackcompose.presentation.search

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.hasangurgur.newsappjetpackcompose.presentation.Dimens
import com.hasangurgur.newsappjetpackcompose.presentation.common.ArticlesList
import com.hasangurgur.newsappjetpackcompose.presentation.common.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigate : (String) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(
                top = Dimens.mediumPadding1,
                start = Dimens.mediumPadding1,
                end = Dimens.mediumPadding1
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchNews) })

        Spacer(modifier = Modifier.height(Dimens.mediumPadding1))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(articles = articles, onClick = {navigate})
        }
    }
}