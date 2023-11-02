package com.zss.demo.data.repository.user

import retrofit2.Call
import retrofit2.http.GET

interface UserApiService {

    @GET("/user")
    fun user() : Call<Any>
}