package com.hasangurgur.newsappjetpackcompose.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hasangurgur.newsappjetpackcompose.R
import com.hasangurgur.newsappjetpackcompose.domain.model.Article
import com.hasangurgur.newsappjetpackcompose.domain.model.Source
import com.hasangurgur.newsappjetpackcompose.presentation.Dimens
import com.hasangurgur.newsappjetpackcompose.presentation.Dimens.ArticleCardSize
import com.hasangurgur.newsappjetpackcompose.presentation.Dimens.ExtraSmallPadding
import com.hasangurgur.newsappjetpackcompose.presentation.Dimens.ExtraSmallPadding2
import com.hasangurgur.newsappjetpackcompose.presentation.Dimens.SmallIconSize
import com.hasangurgur.newsappjetpackcompose.ui.theme.NewsAppTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(Dimens.SmallPadding),

        verticalAlignment = Alignment.Top // Dikey hizalamayı üstten başlat
    ) {
        AsyncImage(
            modifier = Modifier
                .size(Dimens.ArticleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .padding(end = Dimens.MediumPadding),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
        )

        Column(
            modifier = Modifier
                .weight(1f) // Kalan alanı doldur
                .height(IntrinsicSize.Min) // Minimum gerekli yükseklik
        ) {
            Text(
                text = article.title.orEmpty(),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = colorResource(id = R.color.text_title),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = Dimens.ExtraSmallPadding)
            )

            Spacer(modifier = Modifier.weight(1f)) // Boş alanı doldur

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = Dimens.ExtraSmallPadding)
            ) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = colorResource(id = R.color.body),
                    maxLines = 1, // Kaynak adı için maksimum 1 satır
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(0.4f) // Alan paylaşımı
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(Dimens.SmallIconSize),
                    tint = colorResource(id = R.color.body))

                Text(
                    text = article.publishedAt.orEmpty(),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = colorResource(id = R.color.body),
                    maxLines = 1, // Tarih için maksimum 1 satır
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(0.6f) // Alan paylaşımı
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    NewsAppTheme {
        ArticleCard(
            article = Article(
                author = "",
                content = "",
                description = "",
                publishedAt = "2 hours",
                source = Source(id = "", name = "BBC"),
                title = "Haber başlığı hakkında deneme metni",
                url = "",
                urlToImage = ""
            )
        ) {

        }
    }
}
