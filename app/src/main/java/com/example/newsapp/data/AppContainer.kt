package com.example.newsapp.data

import android.content.Context

interface AppContainer {
    val context: Context
}

class DefaultAppContainer(context: Context) : AppContainer {
    override val context = context
}