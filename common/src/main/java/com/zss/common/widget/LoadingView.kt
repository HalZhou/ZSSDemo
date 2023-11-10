package com.zss.common.widget

import android.app.Dialog
import android.content.Context
import android.widget.TextView
import com.zss.common.R

class LoadingView(context: Context) : Dialog(context, R.style.CustomDialogStyle) {

    override fun show() {
        if (isShowing) return
        super.show()
    }
    class Builder(val context: Context) {
        private var message : String? = null

        fun setMessage(msg : String?) : Builder{
            this.message = msg
            return this
        }

        fun build(cancelOnTouchOutside : Boolean = true) : LoadingView{
            val loadingView = LoadingView(context).apply {
                this.setContentView(R.layout.layout_loading_dialog)
                this.setCancelable(cancelOnTouchOutside)
                this.setCanceledOnTouchOutside(cancelOnTouchOutside)
            }
            loadingView.findViewById<TextView>(R.id.tvMessage)?.text = if (message.isNullOrEmpty()) "加载中..." else message
            return loadingView
        }
    }
}