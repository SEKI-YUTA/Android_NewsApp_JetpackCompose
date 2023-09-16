package com.example.newsapp.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.newsapp.R
import com.example.newsapp.ui.component.ErrorMessage
import com.example.newsapp.ui.component.LoadingPlaceholder
import com.example.newsapp.ui.viewmodel.NewsScreenViewModel

@Composable
fun NewsDetailScreen(viewModel: NewsScreenViewModel) {
    val currentArticle = viewModel.currentArticle.collectAsState().value
    val isNetworkConnected = viewModel.isNetworkConnected.collectAsState().value
    val context = LocalContext.current
    var imageState by remember { mutableStateOf<AsyncImagePainter.State?>(null) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (!isNetworkConnected) {
            ErrorMessage(
                message = context.getString(R.string.network_not_connected),
                showReloadButton = false
            )
        } else {
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    currentArticle?.title ?: context.getString(R.string.no_title),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(fontSize = 20.sp)
                )
                if (currentArticle?.author != null) {
                    Text(
                        "${context.getString(R.string.publisher)}: ${currentArticle.author}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                ) {
                    if (currentArticle?.urlToImage != null) {
                        AsyncImage(
                            model = currentArticle.urlToImage,
                            contentDescription = "news image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 8.dp),
                            onState = { state ->
                                imageState = state
                            }
                        )
                        if(imageState is AsyncImagePainter.State.Loading) {
                            LoadingPlaceholder(loadMessage = "")
                        }
                    } else {
                        Image(
                            painter = painterResource(R.drawable.image_placeholder),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 8.dp),
                        )
                    }
                }
                Text(
                    currentArticle?.description ?: context.getString(R.string.no_description),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                TextButton(onClick = {
                    // 掲載ページに飛ぶ処理
                    currentArticle?.url?.let {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                        context.startActivity(intent)
                    }
                }) {
                    Text(
                        stringResource(id = R.string.to_original_page),
                        textDecoration = TextDecoration.Underline
                    )
                }

            }
        }
    }
}