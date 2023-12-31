package com.zss.common.base

import android.app.Application
import com.zss.common.utils.LogUtils
import com.zss.common.utils.Utils

open class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)

        //捕获异常
        Thread.setDefaultUncaughtExceptionHandler { tread, exception ->
            LogUtils.e("DefaultUncaughtExceptionHandler","thread = ${tread.name} exception = ${exception.localizedMessage}")
        }
    }
}