package com.zss.common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zss.common.constants.Constants
import com.zss.common.data.state.UIState
import com.zss.common.utils.Utils
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel(), DefaultLifecycleObserver {

    /**
     * 网络请求管理器
     */
    protected val mCompositeDisposable by lazy { CompositeDisposable() }

    /**
     * 通用UI状态
     */
    private val _uiState = UIState()
    val uiState : UIState get() = _uiState

    /**
     * 点击事件
     */
    open fun onClickView(clickView: View) {

    }

    /**
     * 打开页面
     */
    fun startActivity(clazz : Class<*>,bundle : Bundle? = null) {
        _uiState.startPage.value = hashMapOf<String,Any>().apply {
            put(Constants.CLASS_NAME,clazz)
            bundle?.let { put(Constants.CLASS_BUNDLE,it) }
        }
    }

    /**
     * 关闭页面
     */
    fun finish(bundle: Bundle? = null) {
        _uiState.finishPage.value = bundle
    }

    /**
     * 打开Loading
     */
    fun showLoading(message : String? = null) {
        _uiState.showLoading.value = message
    }

    /**
     * 关闭Loading
     */
    fun hideLoading() {
        _uiState.hideLoading.call()
    }

    protected fun showShort(@StringRes resId: Int) {
        showShort(Utils.getContext().getString(resId))
    }

    protected fun showShort(message: String?) {
        _uiState.toastText.value = message
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
    }

    override fun onCleared() {
        super.onCleared()

    }

    protected fun launchCatch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) =
        viewModelScope.launch {
            try {
                block()
            } catch (e: Throwable) {
                error(e)
            }
        }

    protected fun launch(block: suspend () -> Unit) = viewModelScope.launch {
        block()
    }
}