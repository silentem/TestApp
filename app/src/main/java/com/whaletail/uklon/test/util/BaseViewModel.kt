package com.whaletail.uklon.test.util

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger


open class BaseViewModel : ViewModel(), AnkoLogger {

    val state: MutableLiveData<State> = MutableLiveData()

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    protected fun <T : Any> network(call: Observable<T>): Observable<T> =
            call.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())

    protected fun call(disposable: Disposable) = compositeDisposable.add(disposable)


}