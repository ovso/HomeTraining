package io.github.ovso.hometraining.view.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageViewBinding {

  @JvmStatic
  @BindingAdapter("load")
  fun loadImage(
    view: AppCompatImageView,
    imgUrl: String?
  ) {
    Glide.with(view)
        .load(imgUrl)
        .into(view)
  }
}