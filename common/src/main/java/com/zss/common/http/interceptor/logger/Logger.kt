package com.zss.common.http.interceptor.logger

import okhttp3.internal.platform.Platform

interface Logger {
    fun log(level: Int = Platform.INFO, tag: String?= null, msg: String? = null)

    companion object {
        val DEFAULT: Logger = object : Logger {
            override fun log(level: Int, tag: String?, msg: String?) {
                Platform.get().log("$msg",level, null)
            }
        }
    }
}