package io.github.ovso.hometraining.view.male

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable

class MaleFragmentViewModel(app: Application) : AndroidViewModel(app) {
  private val compositeDisposable by lazy { CompositeDisposable() }

  init {

  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}
