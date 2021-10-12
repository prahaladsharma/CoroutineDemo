package com.coroutine.util

import android.content.Context
import android.net.ConnectivityManager

class SystemUtils {

    companion object {

        val API_KEY:String? = "n8Ajb1UX8fmeg3JnhcZs5Dd2bsEuuHPj"

        fun isNetworkConnected(context: Context): Boolean {
            val connectivitymanager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkinfo = connectivitymanager.activeNetworkInfo
            return if (networkinfo == null || !networkinfo.isConnectedOrConnecting) {
                false
            } else {
                true
            }
        }
    }
}