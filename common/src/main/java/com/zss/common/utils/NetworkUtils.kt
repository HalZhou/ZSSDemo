package com.zss.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkUtils {

    companion object {
        @JvmStatic
        fun isAvailable() : Boolean {
            val connMgr = Utils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
                ?: return false
            val network = connMgr.activeNetwork
            val caps = connMgr.getNetworkCapabilities(network)
            return caps?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        }
    }
}