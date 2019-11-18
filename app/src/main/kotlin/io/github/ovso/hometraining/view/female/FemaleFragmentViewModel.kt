package io.github.ovso.hometraining.view.male

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.github.ovso.hometraining.view.main.BottomNavPosition
import io.github.ovso.hometraining.view.main.BottomNavPosition.MALE
import io.reactivex.disposables.CompositeDisposable

class FemaleFragmentViewModel(app: Application) : AndroidViewModel(app) {
  private val compositeDisposable by lazy { CompositeDisposable() }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}
