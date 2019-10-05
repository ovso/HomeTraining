package io.github.ovso.hometraining.view.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.view.video.VideoAdapter.MyViewHolder
import kotlinx.android.extensions.LayoutContainer

class VideoAdapter : RecyclerView.Adapter<MyViewHolder>() {
  val items = mutableListOf<Any>()
  override fun getItemCount() = 10//items.size

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ) = MyViewHolder.create(parent)

  override fun onBindViewHolder(
    holder: MyViewHolder,
    position: Int
  ) {
    holder.bind(items)
  }

  class MyViewHolder(
    override val containerView: View?
  ) : RecyclerView.ViewHolder(containerView!!), LayoutContainer {

    fun bind(o: Any) {

    }

    companion object {
      fun create(parent: ViewGroup) =
        MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        )
    }

  }
}