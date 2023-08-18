package com.example.newsapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.PlainTooltipBox
import androidx.compose.material3.Text
import androidx.compose.material3.rememberPlainTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.ui.model.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListItem(article: Article, modifier: Modifier = Modifier, onTapAction: () -> Unit) {
    val context = LocalContext.current
    PlainTooltipBox(tooltip = {
        Text(text = article.title ?: "")
    },
        tooltipState = rememberPlainTooltipState(),
    ) {
        ListItem(
            modifier = modifier.clickable {
                // 詳細画面へ遷移する処理が渡される
                onTapAction()
            }.tooltipTrigger(),
            leadingContent = {
                if (article.urlToImage == null) {
                    Image(
                        modifier = Modifier.size(80.dp),
                        painter = painterResource(R.drawable.image_placeholder),
                        contentDescription = "",
                    )
                } else {
                    AsyncImage(
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(80.dp),
                        model = article.urlToImage, contentDescription = ""
                    )
                }
            },
            headlineContent = {
                Text(
                    article.title!!,
                    modifier = Modifier,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            },
        )
    }
}