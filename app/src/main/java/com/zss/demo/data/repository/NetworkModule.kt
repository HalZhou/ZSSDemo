package com.zss.demo.data.repository

import com.zss.common.http.RetrofitClient
import com.zss.demo.data.repository.user.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideUserApiService() : UserApiService {
        return RetrofitClient.getInstance().create(UserApiService::class.java)
    }
}