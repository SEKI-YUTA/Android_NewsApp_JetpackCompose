package com.example.newsapp.other

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun hideSoftKeyboard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow((context as Activity).currentFocus?.windowToken, 0)
}