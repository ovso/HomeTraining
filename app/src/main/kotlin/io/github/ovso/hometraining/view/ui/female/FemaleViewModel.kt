package io.github.ovso.hometraining.view.ui.female

import androidx.databinding.ObservableArrayList
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.view.base.DisposableViewModel

class FemaleViewModel : DisposableViewModel() {
  val items = ObservableArrayList<String>()

  init {
    items.addAll(ResourceProvider.getStringArray(R.array.tabs_title_female).toMutableList())
  }

}
