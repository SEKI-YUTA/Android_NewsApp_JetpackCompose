@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.newsapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.screens.NewsListScreen
import com.example.newsapp.ui.screens.SettingScreen
import com.example.newsapp.ui.viewmodel.MainScreenViewModel
import com.example.newsapp.ui.viewmodel.NewsScreenViewModel

// ここでナビゲーションを行う
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            Surface(shadowElevation = 4.dp) {
                TopAppBar(
                    title = {
                        //　タイトル
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
                    NewsListScreen()
                }
                composable(Screen.SettingScreen.name) {
                    SettingScreen()
                }
            }
        }
    }
}