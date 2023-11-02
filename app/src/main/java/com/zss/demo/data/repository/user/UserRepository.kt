package com.zss.demo.data.repository.user

import com.zss.demo.data.repository.user.local.UserLocalDataSource
import com.zss.demo.data.repository.user.remote.UserRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    val remoteDataSource: UserRemoteDataSource,
    val localDataSource: UserLocalDataSource
) {
}