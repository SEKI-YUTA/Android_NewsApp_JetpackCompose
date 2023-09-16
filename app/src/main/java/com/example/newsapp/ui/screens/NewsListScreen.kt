@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)

package com.example.newsapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.newsapp.R
import com.example.newsapp.ui.component.CategorySelector
import com.example.newsapp.ui.component.NewsList
import com.example.newsapp.ui.component.ErrorMessage
import com.example.newsapp.ui.component.LoadingPlaceholder
import com.example.newsapp.ui.viewmodel.NewsScreenViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsListScreen(navController: NavHostController, viewModel: NewsScreenViewModel) {
    val entries = viewModel.tabsMap.entries
    val currentNewsResponse = viewModel.currentNewsResponse.collectAsState().value
    val isNetworkConnected = viewModel.isNetworkConnected.collectAsState().value
    val horizontalPagerState = rememberPagerState{entries.size}
    val categoryName = entries.elementAt(horizontalPagerState.currentPage).key
    val categoryPrefix = entries.elementAt(horizontalPagerState.currentPage).value
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val isRefreshing = viewModel.isRefreshing.collectAsState().value
    val pullToRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            viewModel.getNews(categoryName, categoryPrefix, true)
        })
    LaunchedEffect(horizontalPagerState.currentPage) {
        if (viewModel.checkResponse(categoryName)) {
            // 取得済みの場合
            println("$categoryName 取得済み")
            viewModel.setCurrentResponse(categoryName)
        } else {
            println("$categoryName 取得中")
            viewModel.getNews(categoryName, categoryPrefix)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullToRefreshState)
    ) {
        Column {
            CategorySelector(
                tabsMap = viewModel.tabsMap,
                currentCategoryName = categoryName,
            ) {
                val idx = viewModel.tabsMap.values.indexOf(it)
                coroutineScope.launch {
                    horizontalPagerState.animateScrollToPage(idx)
                }
            }
            HorizontalPager(
                pageSize = PageSize.Fill,
                state = horizontalPagerState
            ) { idx ->
                println("idx: $idx")
                Box(modifier = Modifier.fillMaxSize()) {
                    Column {
                        if(!isNetworkConnected) {
                            ErrorMessage(
                                message = context.getString(R.string.network_not_connected),
                                showReloadButton = false
                            )
                        } else if (currentNewsResponse == null) {
                            LoadingPlaceholder()
                        } else {
                            NewsList(
                                articleList = currentNewsResponse.articles,
                                viewModel = viewModel,
                                navController = navController,
                                headingText = categoryName
                            )
                        }
                    }
                }
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
    if(currentNewsResponse?.succeed == false) {
        ErrorMessage(reloadAction = {println("reloadAction")})
    }
}



