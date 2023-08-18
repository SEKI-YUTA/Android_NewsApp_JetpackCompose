package com.example.newsapp.ui.screens

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import com.example.newsapp.R
import com.example.newsapp.ui.viewmodel.MainScreenViewModel

@Composable
fun SettingScreen(
    mainScreenViewModel: MainScreenViewModel
) {
    val enableDynamicColor = mainScreenViewModel.enableDynamicColor.collectAsState().value
    val context = LocalContext.current
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
                Text(stringResource(id = R.string.dynamicColorState_title), modifier = Modifier.weight(1f))
                MyToggleItem(
                    onChecked = {enable ->
                        mainScreenViewModel.setEnableDynamicTheme(enable)
                        val preferences = context.getSharedPreferences(context.getString(R.string.preference_key), Context.MODE_PRIVATE)
                        preferences.edit(commit = true) {
                            putBoolean(context.getString(R.string.enable_dynamicColorState_key), enable)
                        }
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