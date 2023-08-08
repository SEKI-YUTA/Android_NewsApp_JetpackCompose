package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.ui.MainHost
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.viewmodel.MainScreenViewModel

// アプリの目的
// テーマとかをきっちり詰める

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainScreenViewModel = viewModel<MainScreenViewModel>(factory = MainScreenViewModel.Factory)
            val enableDynamicColor = mainScreenViewModel.enableDynamicColor.collectAsState().value
            NewsAppTheme(dynamicColor = enableDynamicColor) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainHost(mainScreenViewModel = mainScreenViewModel)
                }
            }
        }
    }
}
