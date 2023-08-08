package com.example.newsapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.newsapp.NewsApplication
import com.example.newsapp.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel(
    currentEnableDynamicColorState: Boolean
) : ViewModel() {
    private val _enableDynamicTheme = MutableStateFlow(currentEnableDynamicColorState)
    val enableDynamicColor = _enableDynamicTheme.asStateFlow()

    fun setEnableDynamicTheme(enable: Boolean) {
        _enableDynamicTheme.value = enable
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val context = (this[APPLICATION_KEY] as NewsApplication).container.context
                val preferences = context.getSharedPreferences(
                    context.getString(R.string.preference_key),
                    Context.MODE_PRIVATE
                )
                val currentEnableDynamicColorState: Boolean = preferences.getBoolean(
                    context.getString(R.string.enable_dynamicColorState_key),
                    true
                )
                println("currentEnableDynamicColorState: $currentEnableDynamicColorState")
                MainScreenViewModel(currentEnableDynamicColorState)
            }
        }
    }
}