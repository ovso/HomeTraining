package kr.co.mybroccoli.transfer.ui.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

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