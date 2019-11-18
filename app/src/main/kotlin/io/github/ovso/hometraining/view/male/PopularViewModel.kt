package io.github.ovso.hometraining.view.male

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable

class PopularViewModel(private var app: Application) : AndroidViewModel(app) {

  var type: Int = 0
  private val compositeDisposable = CompositeDisposable()
  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}
