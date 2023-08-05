package com.example.newsapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.ui.model.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListItem(article: Article, modifier: Modifier = Modifier, onTapAction: () -> Unit) {
    val context = LocalContext.current
    ListItem(
        modifier = modifier.clickable {
            // 詳細ページへ遷移する処理にする予定
            onTapAction()
//            article.url?.let {
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
//                context.startActivity(intent)
//            }
        },
        leadingContent = {
            if(article.urlToImage == null) {
                Image(
                    modifier = Modifier.size(80.dp),
                    painter = painterResource(R.drawable.image_placeholder),
                    contentDescription = "",
                )
            } else {
                AsyncImage(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp),
                    model = article.urlToImage, contentDescription = "")
            }
        },
        headlineText = {
            Text(article.title!!, modifier = Modifier)
        },
        overlineText = {
        },
    )
}