package com.zss.demo.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.zss.common.base.BaseViewModel
import com.zss.demo.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    @Inject lateinit var userRepository : UserRepository

    fun doSomething() {
        Log.d(TAG, "doSomething: ${userRepository.remoteDataSource} ${userRepository.localDataSource}")
    }
    
    companion object {
        private const val TAG = "MainViewModel"
    }
}