package com.example.newsapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.ui.viewmodel.NewsScreenViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsListScreen() {
    val viewModel = viewModel<NewsScreenViewModel>(factory = NewsScreenViewModel.Factory)
    val tagContentState = rememberPagerState(initialPage = 0)
    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager( modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),pageCount = viewModel.tabsMap.size, state = tagContentState) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text("test $it")
                Button(onClick = {
                    viewModel.getNews()
                }) {
                    Text("Call API")
                }
            }
        }
    }
}
