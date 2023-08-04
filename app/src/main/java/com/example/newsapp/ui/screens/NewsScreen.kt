@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.newsapp.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.ui.model.Article
import com.example.newsapp.ui.viewmodel.NewsScreenViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsListScreen() {
    val viewModel = viewModel<NewsScreenViewModel>(factory = NewsScreenViewModel.Factory)
    val entries = viewModel.tabsMap.entries
    val currentNewsResponse = viewModel.currentNewsResponse.collectAsState()
    val tagContentState = rememberPagerState(initialPage = 0)
    val categoryName = entries.elementAt(tagContentState.currentPage).key
    val categoryPrefix = entries.elementAt(tagContentState.currentPage).value
    LaunchedEffect(tagContentState.currentPage) {
        if(viewModel.checkResponse(categoryName)) {
            // 取得済みの場合
            println("$categoryName 取得済み")
            viewModel.setCurrentResponse(categoryName)
        } else {
            println("$categoryName 取得中")
            viewModel.getNews(categoryName,categoryPrefix)
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            pageSize = PageSize.Fill,
            pageCount = viewModel.tabsMap.size, state = tagContentState
        ) { idx ->
            println("idx: $idx")
            Box(modifier = Modifier.fillMaxSize()) {
                Column {
                    Text("$categoryName",modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, bottom = 4.dp), textAlign = TextAlign.Center, style = TextStyle(fontSize = 24.sp))
                    if (currentNewsResponse.value == null) {
                        LoadingPlaceholder()
                    } else {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(currentNewsResponse.value?.articles ?: listOf()) { article ->
                                NewsListItem(article = article)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NewsListItem(article: Article, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    ListItem(
        modifier = modifier.clickable {
            // 詳細ページへ遷移する処理にする予定
          // 今はステートをどうやって保存しておくかが未定なので直接ブラウザでページを開く
            article.url?.let {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                context.startActivity(intent)
            }
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

@Composable
fun LoadingPlaceholder(loadMessage: String = "読み込み中...") {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                loadMessage,
                modifier = Modifier.padding(16.dp),
                style = TextStyle()
            )
            CircularProgressIndicator()
        }
    }
}
