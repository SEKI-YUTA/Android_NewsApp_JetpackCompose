package com.example.newsapp.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.newsapp.R
import com.example.newsapp.component.ErrorMessage
import com.example.newsapp.ui.viewmodel.NewsScreenViewModel

@Composable
fun NewsDetailScreen(viewModel: NewsScreenViewModel) {
    val currentArticle = viewModel.currentArticle.collectAsState()
    val isNetworkConnected = viewModel.isNetworkConnected.collectAsState().value
    val context = LocalContext.current
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        if(!isNetworkConnected) {
            ErrorMessage(
                message = context.getString(R.string.network_not_connected),
                showReloadButton = false
            )
        } else {
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())) {
                Text(
                    currentArticle.value?.title ?: "no title",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(fontSize = 20.sp)
                )
                if(currentArticle.value!!.author != null) {
                    Text("投稿者: ${currentArticle.value!!.author}", modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp))
                }
                if(currentArticle.value!!.urlToImage != null) {
                    AsyncImage(model = currentArticle.value!!.urlToImage, contentDescription = "image", modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp), contentScale = ContentScale.FillWidth)
                } else {
                    Image(
                        painter = painterResource(R.drawable.image_placeholder), contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                    )
                }
                Text(currentArticle.value?.description ?: "no description", modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp))
                TextButton(onClick = {
                    // 掲載ページに飛ぶ処理
                    currentArticle.value!!.url?.let {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                        context.startActivity(intent)
                    }
                }) {
                    Text("掲載ページへ", textDecoration = TextDecoration.Underline)
                }

            }
        }
    }
}