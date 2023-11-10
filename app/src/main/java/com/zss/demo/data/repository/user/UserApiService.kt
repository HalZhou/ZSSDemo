package com.zss.demo.data.repository.user

import com.zss.demo.base.DataRequest
import com.zss.demo.base.DataResponse
import com.zss.demo.data.dto.SsoAuthenticateDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {

    @POST("enocloud/security/authenticate")
    fun login(@Body request : DataRequest<SsoAuthenticateDto>) : Call<DataResponse<String>?>
}