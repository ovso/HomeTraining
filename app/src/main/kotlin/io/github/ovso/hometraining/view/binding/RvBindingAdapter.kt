package io.github.ovso.hometraining.view.binding

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.hometraining.view.male.MaleRvAdapter

object RvBindingAdapter {

  @JvmStatic
  @BindingAdapter("items")
  fun setRvItems(
    rv: RecyclerView,
    items: ObservableList<String>
  ) {
    rv.addItemDecoration(DividerItemDecoration(rv.context, DividerItemDecoration.VERTICAL))
    rv.adapter = MaleRvAdapter().apply { titles.addAll(items) }
  }
}
