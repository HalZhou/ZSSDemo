package com.zss.demo.base

class DataRequest<T>(t : T) {
    val data : MutableList<T> = arrayListOf(t)
}