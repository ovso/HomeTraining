package io.github.ovso.hometraining.view.base

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class DisposableViewModel : ViewModel() {

  protected val compositeDisposable = CompositeDisposable()
  val isLoading = ObservableBoolean(false)
  val isLoadingView = ObservableInt(View.GONE)

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