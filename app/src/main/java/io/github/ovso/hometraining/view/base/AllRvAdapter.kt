package io.github.ovso.hometraining.view.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AllRvAdapter : RecyclerView.Adapter<AllViewHolder>() {
  val titles = mutableListOf<String>()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ) = AllViewHolder.create(parent)

  override fun getItemCount() = titles.count()

  override fun onBindViewHolder(
    holder: AllViewHolder,
    position: Int
  ) {
    holder.bind(title = titles[position])
  }

}