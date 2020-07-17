package com.rxjavasample.repo

import io.reactivex.disposables.CompositeDisposable

open class Repo {
    protected val disposables = CompositeDisposable()

    fun onCleared() {
        disposables.dispose()
    }
}