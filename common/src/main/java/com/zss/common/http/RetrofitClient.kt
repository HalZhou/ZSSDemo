package com.zss.common.http

import com.zss.common.constants.Constants
import com.zss.common.http.cookie.CookieJarImpl
import com.zss.common.http.cookie.store.PersistentCookieStore
import com.zss.common.http.interceptor.CacheInterceptor
import com.zss.common.http.interceptor.HeaderIntercepter
import com.zss.common.http.interceptor.logger.Level
import com.zss.common.http.interceptor.logger.LoggingInterceptor
import com.zss.common.utils.Utils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val baseUrl : String get() = if (Constants.isDebug) "https://enocloudd.enoch-car.com/" else "https://enocloud.enoch-car.com/"

    private val okhttpClient = OkHttpClient.Builder()
        .cookieJar(CookieJarImpl(PersistentCookieStore(Utils.getContext())))
        .addInterceptor(HeaderIntercepter())
        .addInterceptor(LoggingInterceptor.Builder()
            .setLevel(Level.BASIC)
            .tag("Network-OkhttpClient")
            .build())
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okhttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun<T> create(clazz : Class<T>) : T = retrofit.create(clazz)

    companion object {
        @Volatile
        private var instance : RetrofitClient? = null
        @JvmStatic
        fun getInstance() : RetrofitClient {
            return instance ?: synchronized(RetrofitClient::class.java) {
                instance ?: RetrofitClient().also { instance = it }
            }
        }
    }
}