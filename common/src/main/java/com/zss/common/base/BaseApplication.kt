package com.zss.common.base

import android.app.Application
import com.zss.common.utils.Utils

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}