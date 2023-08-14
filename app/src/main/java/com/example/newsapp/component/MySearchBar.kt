package com.example.newsapp.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.Screen

@Composable
fun MySearchBar(
        userInput: String,
        isSearching: Boolean,
        cursorBrush: Brush = if (isSystemInDarkTheme()) Brush.verticalGradient(
            listOf(Color.White, Color.White)
        ) else Brush.verticalGradient(listOf(Color.Black, Color.Black)),
        searchAction: () -> Unit,
        deleteSearchAction: () -> Unit,
        changeUserInputAction: (String) -> Unit,
) {
    Row(
        modifier = Modifier.padding(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            if (!isSearching) {
                IconButton(onClick = {
                    searchAction()
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
                changeUserInputAction(it)
            },
            cursorBrush = cursorBrush,
            textStyle = TextStyle(color = if (isSystemInDarkTheme()) Color.White else Color.Black),
            singleLine = true, modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                searchAction()
            }),
            decorationBox = { innerField ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (userInput.isEmpty()) {
                            Text(
                                text = "キーワードを入力",
                                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                fontSize = 16.sp
                            )
                        }
                        innerField()
                    }
                    if (userInput.isNotEmpty()) {
                        IconButton(onClick = {
                            deleteSearchAction()
                        }) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "",
                                tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                            )
                        }
                    }
                }
            },
        )
    }
}