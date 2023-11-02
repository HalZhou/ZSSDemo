package com.zss.common.utils

import android.util.Log
import com.zss.common.constants.Constants

class LogUtils {

    companion object {
        @JvmStatic
        fun d(tag: String, message: String?) {
            if (Constants.isDebug) {
                Log.d(tag, message ?: "--")
            }
        }

        @JvmStatic
        fun e(tag: String, message: String?) {
            if (Constants.isDebug) {
                Log.e(tag, message ?: "--")
            }
        }
    }
}