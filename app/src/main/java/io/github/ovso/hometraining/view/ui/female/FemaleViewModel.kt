package io.github.ovso.hometraining.view.ui.female

import androidx.databinding.ObservableArrayList
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.view.base.DisposableViewModel

class FemaleViewModel : DisposableViewModel() {
  val items = ObservableArrayList<String>()
  val queries = ObservableArrayList<String>()

  init {
    items.addAll(ResourceProvider.getStringArray(R.array.tabs_title_female))
    queries.addAll(ResourceProvider.getStringArray(R.array.queries_female))
  }

}
