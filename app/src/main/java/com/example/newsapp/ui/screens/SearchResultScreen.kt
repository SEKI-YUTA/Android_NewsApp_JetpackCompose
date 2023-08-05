package com.example.newsapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsapp.component.ArticleList
import com.example.newsapp.component.ErrorMessage
import com.example.newsapp.component.LoadingPlaceholder
import com.example.newsapp.ui.viewmodel.NewsScreenViewModel

@Composable
fun SearchResultScreen(navController: NavHostController, viewModel: NewsScreenViewModel) {
    val isSearching = viewModel.isSearching.collectAsState()
    val currentSearchResult = viewModel.currentSearchResult.collectAsState()
    Box {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                "検索結果",
                modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp, top = 8.dp),
                style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center)
            )
            Text("該当数：${currentSearchResult.value?.totalResults}件",
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp, end = 8.dp),
                style = TextStyle(fontSize = 14.sp, textAlign = TextAlign.End))
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if(isSearching.value) {
                    LoadingPlaceholder()
                } else {
                    ArticleList(
                        articleList = currentSearchResult.value?.articles,
                        viewModel = viewModel,
                        navController = navController
                    )
                }
            }

        }
        if(currentSearchResult.value?.succeed == false) {
            ErrorMessage(showReloadButton = false)
        }
    }
}