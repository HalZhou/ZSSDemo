package com.zss.common.http.interceptor

import com.zss.common.utils.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 无网络状态下智能读取缓存的拦截器
 */
class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (NetworkUtils.isAvailable()) {
            val response = chain.proceed(request)
            //read from cache for 60s
            val maxAge = 60
            return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, max-age=$maxAge")
                .build()
        } else {
            //读取缓存信息
            val newRequest = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
            val response = chain.proceed(newRequest)
            //set cache times in 3 days
            val maxStale = 60 * 60 * 24 * 3
            return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control","public, only-if-cached, max-stale=$maxStale")
                .build()
        }
    }
}