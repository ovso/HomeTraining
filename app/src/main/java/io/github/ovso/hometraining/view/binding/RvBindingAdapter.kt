package io.github.ovso.hometraining.view.binding

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.hometraining.view.base.AllRvAdapter

object RvBindingAdapter {

  @JvmStatic
  @BindingAdapter("items", "queries", requireAll = true)
  fun setRvItems(
    rv: RecyclerView,
    items: ObservableList<String>,
    _queries: ObservableList<String>
  ) {
    rv.addItemDecoration(DividerItemDecoration(rv.context, DividerItemDecoration.VERTICAL))
    rv.adapter = AllRvAdapter().apply {
      titles.addAll(items)
      queries.addAll(_queries)
    }
  }
}