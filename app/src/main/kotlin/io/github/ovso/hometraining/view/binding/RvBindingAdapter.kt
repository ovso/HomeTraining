package io.github.ovso.hometraining.view.binding

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

object RvBindingAdapter {

  @JvmStatic
  @BindingAdapter("items")
  fun setRvItems(
    rv: RecyclerView,
    items: ObservableList<String>
  ) {
    Timber.d("items = $items")
  }
}
