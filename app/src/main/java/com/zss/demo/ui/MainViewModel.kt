package com.zss.demo.ui

import android.provider.ContactsContract.Data
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zss.common.base.BaseViewModel
import com.zss.common.utils.ToastUtils
import com.zss.demo.base.DataResponse
import com.zss.demo.data.dto.SsoAuthenticateDto
import com.zss.demo.data.repository.handleDataResponse
import com.zss.demo.data.repository.launchRequest
import com.zss.demo.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
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