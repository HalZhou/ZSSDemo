package com.zss.demo

import android.app.Application
import com.zss.common.base.BaseApplication
import com.zss.common.utils.LogUtils
import com.zss.common.utils.Utils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        /*Utils.init(this)

        //捕获异常
        Thread.setDefaultUncaughtExceptionHandler { tread, exception ->
            LogUtils.e("DefaultUncaughtExceptionHandler","thread = ${tread.name} exception = ${exception.localizedMessage}")
        }*/
    }
}