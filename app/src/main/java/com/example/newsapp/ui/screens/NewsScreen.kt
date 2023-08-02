package com.example.newsapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.ui.viewmodel.NewsScreenViewModel

@Composable
fun NewsListScreen() {
    val viewModel = viewModel<NewsScreenViewModel>()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column() {
            Text(text = "NewsListScreen")
            Button(onClick = {
                viewModel.getNews()
            }) {
                Text("call api")
            }
        }
    }
}