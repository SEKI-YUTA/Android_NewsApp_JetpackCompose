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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.R
import com.example.newsapp.other.hideSoftKeyboard
import com.example.newsapp.ui.screens.NewsDetailScreen
import com.example.newsapp.ui.screens.NewsListScreen
import com.example.newsapp.ui.screens.SearchResultScreen
import com.example.newsapp.ui.screens.SettingScreen
import com.example.newsapp.ui.viewmodel.NewsScreenViewModel

// ここでナビゲーションを行う
@Composable
fun MainHost() {
    val navController = rememberNavController()
    val newsScreenViewModel = viewModel<NewsScreenViewModel>(factory = NewsScreenViewModel.Factory)
    val context = LocalContext.current
    Scaffold(
        topBar = {
            Surface(shadowElevation = 4.dp) {
                TopAppBar(
                    title = {
                        //　タイトル
                        val isSearching = newsScreenViewModel.isSearching.collectAsState()
                        var userInput by remember { mutableStateOf("")}
                        Row(
                            modifier = Modifier.padding(2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                if (!isSearching.value) {
                                    IconButton(onClick = {
//                                        searchAction()
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                            contentDescription = null
                                        )
                                    }
                                } else {
                                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                                }
                            }
                            BasicTextField(
                                value = userInput,
                                onValueChange = {
                                    userInput = it
                                },
                                textStyle = TextStyle(color = if(isSystemInDarkTheme()) Color.White else Color.Black),
                                singleLine = true, modifier = Modifier.weight(1f),
                                keyboardActions = KeyboardActions(onDone = {
                                    newsScreenViewModel.searchNews(userInput)
                                    navController.navigate(Screen.SearchResultScreen.name)
                                    hideSoftKeyboard(context)
                                }),
                                decorationBox = { innerField ->
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                                            if(userInput.isEmpty()) {
                                                Text(
                                                    text = "キーワードを入力",
                                                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                                    fontSize = 16.sp
                                                )}
                                            innerField()
                                        }
                                        if (userInput.isNotEmpty()) {
                                            IconButton(onClick = {
                                                userInput = ""
                                                navController.popBackStack()
                                                hideSoftKeyboard(context)
                                            }) {
                                                Icon(Icons.Default.Close, contentDescription = "", tint = if(isSystemInDarkTheme()) Color.White else Color.Black)
                                            }
                                        }
                                    }
                                },
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            println("go to setting screen")
                            navController.navigate(Screen.SettingScreen.name)
                        }) {
                            Icon(imageVector = Icons.Default.Settings, contentDescription = "setting")
                        }
                    },
                )
            }
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            NavHost(navController = navController, startDestination = Screen.NewsScreen.name) {
                // ここで画面遷移を行う
                composable(Screen.NewsScreen.name) {
                    NewsListScreen(navController = navController, viewModel = newsScreenViewModel)
                }
                composable(Screen.NewsDetailScreen.name) {
                    NewsDetailScreen(viewModel = newsScreenViewModel)
                }
                composable(Screen.SettingScreen.name) {
                    SettingScreen()
                }
                composable(Screen.SearchResultScreen.name) {
                    SearchResultScreen(navController = navController, viewModel = newsScreenViewModel)
                }
            }
        }
    }
}