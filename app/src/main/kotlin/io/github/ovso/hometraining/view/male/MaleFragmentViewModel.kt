package io.github.ovso.hometraining.view.male

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.github.ovso.hometraining.view.main.BottomNavPosition
import io.github.ovso.hometraining.view.main.BottomNavPosition.MALE
import io.reactivex.disposables.CompositeDisposable

class MaleFragmentViewModel(app: Application) : AndroidViewModel(app) {
  private val compositeDisposable by lazy { CompositeDisposable() }
  var typeLiveData = MutableLiveData<BottomNavPosition>()

  init {
    typeLiveData.postValue(MALE)
  }
  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}
