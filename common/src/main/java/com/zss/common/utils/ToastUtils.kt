package com.zss.common.utils

import android.widget.Toast
import androidx.annotation.StringRes
import com.zss.common.R

object ToastUtils {

    private var lastShowTime = 0L

    private var lastMessage: String = ""

    @JvmStatic
    fun showShort(@StringRes resId : Int) {
        val message = Utils.getContext().getString(resId)
        showShort(message)
    }

    @JvmStatic
    fun showShort(message: String?) {
        if (message.isNullOrEmpty()) return
        if (lastMessage == message || System.currentTimeMillis() - lastShowTime < 200) return

        Toast.makeText(Utils.getContext(), message, Toast.LENGTH_SHORT).show()
        lastMessage = message
        lastShowTime = System.currentTimeMillis()
    }
}