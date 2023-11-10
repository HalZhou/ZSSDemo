package com.zss.common.data.state

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.zss.common.data.event.SingleLiveEvent

class UIState {

    /**
     * 开启Loading
     */
    val showLoading = SingleLiveEvent<String>()

    /**
     * 关闭Loading
     */
    val hideLoading = SingleLiveEvent<Boolean>()


    /**
     * 弹窗
     */
    val toastText = SingleLiveEvent<String>()

    /**
     * 开启页面
     */
    val startPage = SingleLiveEvent<Map<String,Any>>()

    /**
     * 关闭页面
     */
    val finishPage = SingleLiveEvent<Bundle?>()

}