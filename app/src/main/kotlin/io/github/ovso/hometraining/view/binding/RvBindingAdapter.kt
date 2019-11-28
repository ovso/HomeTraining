package io.github.ovso.hometraining.view.binding

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.hometraining.view.male.MaleRvAdapter

object RvBindingAdapter {

  @JvmStatic
  @BindingAdapter("items")
  fun setRvItems(
    rv: RecyclerView,
    items: ObservableList<String>
  ) {
    rv.adapter = MaleRvAdapter().apply { titles.addAll(items) }
  }
}
