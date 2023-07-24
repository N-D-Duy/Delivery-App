package com.example.deliveryapp.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager


class CheckNetwork {
    fun isConnected(context: Context): Boolean {
        val connMgr = context.getSystemService(Activity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}