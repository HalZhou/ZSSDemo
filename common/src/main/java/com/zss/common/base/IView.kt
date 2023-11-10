package com.zss.common.base

import android.os.Bundle

interface IView {
    /**
     * 初始化数据
     */
    fun initDatas()

    /**
     * 初始化View
     */
    fun initViews(savedStateHandle: Bundle?)

    /**
     * 初始化页面事件监控
     */
    fun initViewObservables()

    /**
     * 显示Toast
     */
    fun showShort(toast : String?)
}