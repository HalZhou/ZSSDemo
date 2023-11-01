package com.zss.common.utils

import android.content.Context

class Utils {

    companion object {
        @JvmStatic
        private var mContext : Context? = null

        @JvmStatic
        fun getContext() : Context = mContext!!

        @JvmStatic
        fun init(context : Context) {
            mContext = context
        }
    }
}