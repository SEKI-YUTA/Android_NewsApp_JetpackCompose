@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.newsapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsapp.component.ArticleList
import com.example.newsapp.component.LoadingPlaceholder
import com.example.newsapp.ui.viewmodel.NewsScreenViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsListScreen(navController: NavHostController, viewModel: NewsScreenViewModel) {
    val entries = viewModel.tabsMap.entries
    val currentNewsResponse = viewModel.currentNewsResponse.collectAsState()
    val tagContentState = rememberPagerState(initialPage = 0)
    val categoryName = entries.elementAt(tagContentState.currentPage).key
    val categoryPrefix = entries.elementAt(tagContentState.currentPage).value
    LaunchedEffect(tagContentState.currentPage) {
        if (viewModel.checkResponse(categoryName)) {
            // 取得済みの場合
            println("$categoryName 取得済み")
            viewModel.setCurrentResponse(categoryName)
        } else {
            println("$categoryName 取得中")
            viewModel.getNews(categoryName, categoryPrefix)
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
                    Text(
                        "$categoryName",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, bottom = 4.dp),
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontSize = 24.sp)
                    )
                    if (currentNewsResponse.value == null) {
                        LoadingPlaceholder()
                    } else {
                        ArticleList(
                            articleList = currentNewsResponse.value?.articles,
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}



