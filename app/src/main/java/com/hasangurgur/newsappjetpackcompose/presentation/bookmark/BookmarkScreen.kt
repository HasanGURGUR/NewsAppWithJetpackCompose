package com.hasangurgur.newsappjetpackcompose.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.hasangurgur.newsappjetpackcompose.R
import com.hasangurgur.newsappjetpackcompose.domain.model.Article
import com.hasangurgur.newsappjetpackcompose.presentation.Dimens
import com.hasangurgur.newsappjetpackcompose.presentation.common.ArticlesList

@Composable
fun BookmarkScreen(
    state: BookmartState,
    navigateToDetails: (Article) -> Unit,

    ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                top = Dimens.mediumPadding1,
                start = Dimens.mediumPadding1,
                end = Dimens.mediumPadding1
            )
    ) {
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title),
        )

        Spacer(modifier = Modifier.height(Dimens.mediumPadding1))

        ArticlesList(
            articles = state.articles,
            onClick = { navigateToDetails(it) })
    }

}