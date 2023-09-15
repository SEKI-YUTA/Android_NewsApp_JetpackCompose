@file:OptIn(ExperimentalFoundationApi::class)

package com.example.newsapp.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsapp.ui.Screen
import com.example.newsapp.ui.model.Article
import com.example.newsapp.ui.viewmodel.NewsScreenViewModel

@Composable
fun NewsList(
    articleList: List<Article>?,
    viewModel: NewsScreenViewModel,
    navController: NavHostController,
    // titleTextとかにした方がいいかな
    categoryName: String
) {
    val lazyListState = rememberLazyListState()
    // 多分この値でヘッダーを下方向へ動かす事によってゆっくり動くようにしてる
    val headerTranslationY by remember {
        derivedStateOf {
            when {
                lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty()&&
                        lazyListState.firstVisibleItemIndex == 0 ->
                    lazyListState.firstVisibleItemScrollOffset * 0.8f
                else -> 0f
            }
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = lazyListState,
    ) {
        item {
            Text(
                "$categoryName",
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 4.dp)
                    .graphicsLayer {
                        translationY = headerTranslationY
                    }
                ,
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 24.sp)
            )
        }
        items(articleList ?: listOf()) { article ->
            NewsListItem(article = article, onTapAction = {
                viewModel.setCurrentArticle(article)
                navController.navigate(Screen.NewsDetailScreen.name)
            })
        }
    }
}