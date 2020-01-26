package io.github.ovso.hometraining.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.gson.JsonElement
import io.github.ovso.hometraining.R

class GenderAdapter : ListAdapter<JsonElement, GenderViewHolder>(diffUtil) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenderViewHolder {
        return GenderViewHolder(R.layout.item_all, parent)
    }

    override fun onBindViewHolder(
        holder: GenderViewHolder,
        position: Int
    ) {
        holder.onBindViewHolder(getItem(position))
    }
}

val diffUtil = object : DiffUtil.ItemCallback<JsonElement>() {
    override fun areItemsTheSame(
        oldItem: JsonElement,
        newItem: JsonElement
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: JsonElement,
        newItem: JsonElement
    ): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}
