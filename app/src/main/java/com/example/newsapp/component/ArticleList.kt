package com.example.newsapp.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.newsapp.ui.Screen
import com.example.newsapp.ui.model.Article
import com.example.newsapp.ui.viewmodel.NewsScreenViewModel

@Composable
fun ArticleList(articleList: List<Article>?, viewModel: NewsScreenViewModel, navController: NavHostController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(articleList ?: listOf()) { article ->
            NewsListItem(article = article, onTapAction = {
                viewModel.setCurrentArticle(article)
                navController.navigate(Screen.NewsDetailScreen.name)
            })
        }
    }
}