package com.example.newsapp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategorySelector(
    tabsMap: Map<String, String>,
    currentCategoryName: String,
    onTabSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        tabsMap.map {
        map ->
            CategoryTab(
                categoryMapEntry = map,
                isSelected = map.key == currentCategoryName,
                onTabSelected = onTabSelected
            )
        }
    }
}

@Composable
fun CategoryTab(
    categoryMapEntry: Map.Entry<String,String>,
    isSelected: Boolean,
    onTabSelected: (String) -> Unit
) {
    var tabWidthPx by remember { mutableStateOf(0) }
    val density = LocalDensity.current
    val tabWidthDp = with(density) { tabWidthPx.toDp()}
    Box(
      modifier = Modifier
          .background(MaterialTheme.colorScheme.surfaceVariant)
          .padding(8.dp)
          .clickable {
                onTabSelected(categoryMapEntry.value)
          }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                categoryMapEntry.key,
                modifier = Modifier.onGloballyPositioned {
                    coordinates ->
                    tabWidthPx = coordinates.size.width
                },
                style = TextStyle(
                    fontSize =  20.sp
                )
            )
            Spacer(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .width(tabWidthDp.minus(16.dp))
                    .height(2.dp)
                    .background(if(isSelected) MaterialTheme.colorScheme.surfaceTint else MaterialTheme.colorScheme.surface)
            )
        }
    }
}

