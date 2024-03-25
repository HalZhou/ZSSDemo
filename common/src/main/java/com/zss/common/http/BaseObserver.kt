package com.zss.common.http

import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class BaseObserver<T>(val compositeDisposable: CompositeDisposable) : Observer<T> {
    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onError(e: Throwable) {
        // 异常
        
    }

    override fun onComplete() {
        // 结束
    }

    override fun onNext(t: T) {
        // onNext
    }


    fun onFailure(failureCode : String,failureMessage : String?) {

    }


}