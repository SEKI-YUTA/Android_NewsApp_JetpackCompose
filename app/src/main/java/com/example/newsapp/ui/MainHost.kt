@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.newsapp.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.component.MySearchBar
import com.example.newsapp.other.AppUtil
import com.example.newsapp.ui.screens.NewsDetailScreen
import com.example.newsapp.ui.screens.NewsListScreen
import com.example.newsapp.ui.screens.SearchResultScreen
import com.example.newsapp.ui.screens.SettingScreen
import com.example.newsapp.ui.viewmodel.MainScreenViewModel
import com.example.newsapp.ui.viewmodel.NewsScreenViewModel

// ここでナビゲーションを行う
@Composable
fun MainHost(
    mainScreenViewModel: MainScreenViewModel
) {
    val navController = rememberNavController()
    val newsScreenViewModel = viewModel<NewsScreenViewModel>(factory = NewsScreenViewModel.Factory)
    val currentScreen = newsScreenViewModel.currentScreen.collectAsState().value
    val context = LocalContext.current
    Scaffold(
        topBar = {
            Surface(shadowElevation = 4.dp) {
                TopAppBar(
                    title = {
                        // 設定画面では検索バー（アップバー）を表示しない
                        if(currentScreen != Screen.SettingScreen) {
                            val isSearching = newsScreenViewModel.isSearching.collectAsState().value
                            var userInput by remember { mutableStateOf("") }
                            MySearchBar(
                                userInput = userInput,
                                isSearching = isSearching,
                                searchAction = {
                                    newsScreenViewModel.searchNews(userInput)
                                    navController.navigate(Screen.SearchResultScreen.name) {
                                        launchSingleTop = true
                                    }
                                    AppUtil.hideSoftKeyboard(context)
                                },
                                deleteSearchAction =  {
                                    userInput = ""
                                    navController.popBackStack()
                                    AppUtil.hideSoftKeyboard(context)
                                },
                                changeUserInputAction = {
                                    userInput = it
                                }
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            println("go to setting screen")
                            navController.navigate(Screen.SettingScreen.name) {
                                launchSingleTop = true
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "setting"
                            )
                        }
                    },
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavHost(navController = navController, startDestination = Screen.NewsScreen.name) {
                // ここで画面遷移を設定している
                composable(Screen.NewsScreen.name) {
                    newsScreenViewModel.setCurrentScreen(Screen.NewsScreen)
                    NewsListScreen(navController = navController, viewModel = newsScreenViewModel)
                }
                composable(Screen.NewsDetailScreen.name) {
                    newsScreenViewModel.setCurrentScreen(Screen.NewsDetailScreen)
                    NewsDetailScreen(viewModel = newsScreenViewModel)
                }
                composable(Screen.SettingScreen.name) {
                    newsScreenViewModel.setCurrentScreen(Screen.SettingScreen)
                    SettingScreen(mainScreenViewModel = mainScreenViewModel)
                }
                composable(Screen.SearchResultScreen.name) {
                    newsScreenViewModel.setCurrentScreen(Screen.SearchResultScreen)
                    SearchResultScreen(
                        navController = navController,
                        viewModel = newsScreenViewModel
                    )
                }
            }
        }
    }
}