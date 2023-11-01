package com.zss.common.http

import com.zss.common.utils.Utils
import com.zss.common.constants.Constants
import com.zss.common.http.cookie.CookieJarImpl
import com.zss.common.http.cookie.store.PersistentCookieStore
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val baseUrl : String get() = if (Constants.isDebug) "https://enocloudd.enoch-car.com/" else "https://enocloud.enoch-car.com/"

    private val okhttpClient = OkHttpClient.Builder()
        .cookieJar(CookieJarImpl(PersistentCookieStore(Utils.getContext())))
        .addInterceptor(HeaderIntercepter())
        .addInterceptor(HttpLoggingInterceptor().apply {
            this.setLevel(HttpLoggingInterceptor.Level.BASIC)
        })
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