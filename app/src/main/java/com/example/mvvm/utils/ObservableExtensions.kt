package com.example.mvvm.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

val <T> Observable<T>.subscribeOnIO: Observable<T>
    get() = subscribeOn(Schedulers.io())

val <T> Observable<T>.observeOnMainThread: Observable<T>
    get() = observeOn(AndroidSchedulers.mainThread())