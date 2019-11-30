package io.github.ovso.hometraining.view.ui.male

import androidx.databinding.ObservableArrayList
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.view.base.DisposableViewModel
import io.reactivex.disposables.CompositeDisposable

class MaleViewModel : DisposableViewModel() {
  private val compositeDisposable by lazy { CompositeDisposable() }

  val items = ObservableArrayList<String>()

  init {
    items.addAll(ResourceProvider.getStringArray(R.array.tabs_title_male).toMutableList())
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}