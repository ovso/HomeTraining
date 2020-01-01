package io.github.ovso.hometraining.view.ui.male

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class MaleAdapter : ListAdapter<MaleItem, MaleViewHolder>(DIFF_UTIL) {
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): MaleViewHolder {
    return MaleViewHolder(parent)
  }

  override fun onBindViewHolder(
    holder: MaleViewHolder,
    position: Int
  ) {
    holder.onBindViewHolder(getItem(position))
  }

}

val DIFF_UTIL = object : DiffUtil.ItemCallback<MaleItem>() {
  override fun areItemsTheSame(
    oldItem: MaleItem,
    newItem: MaleItem
  ): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(
    oldItem: MaleItem,
    newItem: MaleItem
  ): Boolean {
    return oldItem == newItem
  }
}