package com.zss.demo.data.repository.user.remote

import com.zss.demo.base.DataRequest
import com.zss.demo.data.dto.SsoAuthenticateDto
import com.zss.demo.data.repository.user.UserApiService
import retrofit2.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSource @Inject constructor(private val userApiService: UserApiService) {

    suspend fun toLogin(request : SsoAuthenticateDto) = userApiService.login(DataRequest(request)).await()
}