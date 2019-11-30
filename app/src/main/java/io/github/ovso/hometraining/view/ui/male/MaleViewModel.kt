package io.github.ovso.hometraining.view.ui.male

import androidx.databinding.ObservableArrayList
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.view.base.DisposableViewModel

class MaleViewModel : DisposableViewModel() {
  val items = ObservableArrayList<String>()
  val queries = ObservableArrayList<String>()

  init {
    items.addAll(ResourceProvider.getStringArray(R.array.tabs_title_male))
    queries.addAll(ResourceProvider.getStringArray(R.array.queries_male))
  }
}