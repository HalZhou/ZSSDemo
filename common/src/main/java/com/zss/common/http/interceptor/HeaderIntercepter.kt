package com.zss.common.http.interceptor

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.StringBuilder

class HeaderIntercepter : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val appVersion = "1.0"
        val machineModel = Build.MODEL
        val osVersion = Build.VERSION.SDK_INT
        val manuFecturer = Build.MANUFACTURER
        val sb = StringBuilder()
        sb.append("ANDROID[")
            .append("MODEL=$machineModel")
            .append(",MANUFACTURER=$manuFecturer")
            .append(",OSVERSION=$osVersion")
            .append(",APPVERSION=$appVersion")
            .append("]")
        val build = chain.request().newBuilder()
            .addHeader("TERMINAL",sb.toString())
        val request = build.build()
        return chain.proceed(request)
    }
}