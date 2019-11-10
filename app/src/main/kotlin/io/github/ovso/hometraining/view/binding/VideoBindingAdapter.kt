package io.github.ovso.hometraining.view.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonArray
import io.github.ovso.hometraining.view.video.VideoAdapter
import timber.log.Timber

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
}