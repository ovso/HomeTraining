package io.github.ovso.hometraining.view.ui.female

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class FemaleAdapter : ListAdapter<FemaleItem, FemaleViewHolder>(DIFF_UTIL) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FemaleViewHolder {
        return FemaleViewHolder(parent)
    }

    override fun onBindViewHolder(
        holder: FemaleViewHolder,
        position: Int
    ) {
        holder.onBindViewHolder(getItem(position))
    }
}

val DIFF_UTIL = object : DiffUtil.ItemCallback<FemaleItem>() {
    override fun areItemsTheSame(
        oldItem: FemaleItem,
        newItem: FemaleItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: FemaleItem,
        newItem: FemaleItem
    ): Boolean {
        return oldItem == newItem
    }
}
