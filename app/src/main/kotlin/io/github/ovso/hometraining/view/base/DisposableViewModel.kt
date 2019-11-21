package io.github.ovso.hometraining.view.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class DisposableViewModel : ViewModel() {
    private val compositeDisposable by lazy { CompositeDisposable() }
    open fun addDispose(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}