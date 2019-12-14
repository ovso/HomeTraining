package io.github.ovso.hometraining.view.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class DisposableViewModel : ViewModel() {

  private val compositeDisposable = CompositeDisposable()
  val isLoading = ObservableField<Boolean>(false)

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
}