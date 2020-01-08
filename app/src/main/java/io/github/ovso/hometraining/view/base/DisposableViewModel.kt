package io.github.ovso.hometraining.view.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class DisposableViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()
    val isLoading = ObservableBoolean(false)

    override fun onCleared() {
        clearDisposable()
        super.onCleared()
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun clearDisposable() {
        compositeDisposable.clear()
    }

    protected fun showLoading() {
        isLoading.set(true)
    }

    protected fun hideLoading() {
        isLoading.set(false)
    }
}
