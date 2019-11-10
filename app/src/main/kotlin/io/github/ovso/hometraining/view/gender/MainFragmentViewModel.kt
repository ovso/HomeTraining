package io.github.ovso.hometraining.view.gender

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.github.ovso.hometraining.view.main.BottomMenu
import io.reactivex.disposables.CompositeDisposable

class MainFragmentViewModel(app: Application) : AndroidViewModel(app) {
  private val compositeDisposable by lazy { CompositeDisposable() }
  var typeLiveData = MutableLiveData<BottomMenu>()
  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}
