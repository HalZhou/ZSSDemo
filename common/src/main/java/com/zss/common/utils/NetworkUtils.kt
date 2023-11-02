package com.zss.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log

class NetworkUtils {

    companion object {

        /**
         * 网络是否连接
         */
        @JvmStatic
        fun isAvailable() : Boolean {
            val connMgr = Utils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
                ?: return false
            val network = connMgr.activeNetwork
            //包括 路由，地址，名称，代理和DNS
            val linkProerties = connMgr.getLinkProperties(network)

            //包括 transports（WIFI，Mobile；）和capabilities
            val caps = connMgr.getNetworkCapabilities(network)
            return caps?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        }

        /**
         * 检测网络连接的类型
         */
        @JvmStatic
        fun checkNetworkType() : Int? {
            val connMgr = Utils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
                ?: return null
            val network = connMgr.activeNetwork ?: return null
            //包括 transports（WIFI，Mobile；）和capabilities
            val caps = connMgr.getNetworkCapabilities(network)
            val hasWiFi = caps?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            val hasMobile = caps?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            return when {
                hasWiFi == true -> NetworkCapabilities.TRANSPORT_WIFI
                hasMobile == true -> NetworkCapabilities.TRANSPORT_CELLULAR
                else -> null
            }
        }
    }
}