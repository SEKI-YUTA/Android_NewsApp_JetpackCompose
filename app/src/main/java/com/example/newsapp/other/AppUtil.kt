package com.example.newsapp.other

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.view.inputmethod.InputMethodManager
import kotlinx.coroutines.flow.MutableStateFlow

class AppUtil {
    companion object {
        fun hideSoftKeyboard(context: Context) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow((context as Activity).currentFocus?.windowToken, 0)
        }


        fun checkNetworkConnection(context: Context, status: MutableStateFlow<Boolean>) {
//            if(Build.VERSION.SDK_INT >= 29) {
//                // api29以降の場合
//                return false
//            } else {
//                // api29以前の場合
//                val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//                connectionManager.addDefaultNetworkActiveListener {
//                    // ネットワーク接続状態が変化した時に呼ばれる
//                    println("network status changed")
//                }
//                connectionManager.all
//                val activeNetwork = connectionManager.activeNetworkInfo
//                return activeNetwork != null && activeNetwork.isConnected
//            }
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            cm.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: android.net.Network) {
                    super.onAvailable(network)
                    status.value = true
                    println("network status changed ${status.value}")
                }

                override fun onLost(network: android.net.Network) {
                    super.onLost(network)
                    status.value = false
                    println("network status changed ${status.value}")
                }
            })
            status.value = activeNetwork != null && activeNetwork.isConnected
        }
    }
}
