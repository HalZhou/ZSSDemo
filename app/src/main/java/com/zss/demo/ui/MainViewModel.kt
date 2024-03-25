package com.zss.demo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zss.common.base.BaseViewModel
import com.zss.demo.data.dto.SsoAuthenticateDto
import com.zss.demo.data.repository.launchRequest
import com.zss.demo.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var userRepository: UserRepository

    val phone = MutableLiveData<String>()

    val password = MutableLiveData<String>()
    fun getUser() {

        viewModelScope.launchRequest({
            val request = SsoAuthenticateDto().apply {
                this.username = phone.value
                this.password = this@MainViewModel.password.value
            }
            userRepository.toLogin(request)
        }, onSuccess = {
            showShort("登录成功")
        }, onFailure = {code,message->
            showShort(message)
        })
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}