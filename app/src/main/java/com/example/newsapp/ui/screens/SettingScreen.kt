package com.example.newsapp.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.viewmodel.MainScreenViewModel

@Composable
fun SettingScreen(
    mainScreenViewModel: MainScreenViewModel
) {
    val enableDynamicColor = mainScreenViewModel.enableDynamicColor.collectAsState().value
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("ダイナミックテーマ", modifier = Modifier.weight(1f))
                MyToggleItem(
                    onChecked = {enable ->
                        mainScreenViewModel.setEnableDynamicTheme(enable)
                    },
                    checked = enableDynamicColor
                )
            }
        }
    }
}


@Composable
fun MyToggleItem(
    checkedMessage: String = "ON",
    uncheckedMessage: String = "OFF",
    onChecked: (Boolean) -> Unit,
    checked: Boolean,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(if (checked) checkedMessage else uncheckedMessage, modifier = Modifier.padding(end = 4.dp))
        Switch(
            checked = checked,
            onCheckedChange = onChecked
        )
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MyToggleItemPreview() {
    MyToggleItem(
        onChecked = {},
        checked = true
    )
}