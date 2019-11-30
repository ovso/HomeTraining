package io.github.ovso.hometraining.view.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.hometraining.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_all.tv_all_item_title

class AllViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView!!),
    LayoutContainer {

  fun bind(title: String) {
    tv_all_item_title.text = title
  }

  companion object {
    fun create(parent: ViewGroup): AllViewHolder {
      return AllViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.item_all, parent, false)
      )
    }
  }
}
