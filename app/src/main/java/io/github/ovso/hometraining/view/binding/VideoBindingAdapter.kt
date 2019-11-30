package io.github.ovso.hometraining.view.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.JsonArray
import io.github.ovso.hometraining.view.ui.video.VideoAdapter

object VideoBindingAdapter {

  @JvmStatic
  @BindingAdapter("items")
  fun setVideoItems(
    rv: RecyclerView,
    items: JsonArray?
  ) {
    items?.let {
      rv.adapter = VideoAdapter().apply {
        this.items = it
      }
    }
  }

  @JvmStatic
  @BindingAdapter("load")
  fun loadImage(
    view: AppCompatImageView?,
    imgUrl: String?
  ) {
    view?.let {
      Glide.with(it)
          .load(imgUrl)
          .into(view)
    }
  }
}