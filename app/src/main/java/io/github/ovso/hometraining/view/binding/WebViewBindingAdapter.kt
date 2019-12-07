package io.github.ovso.hometraining.view.binding

import android.webkit.WebView
import androidx.databinding.BindingAdapter

object WebViewBindingAdapter {
  @JvmStatic
  @BindingAdapter("load_url")
  fun loadUrl(
    view: WebView,
    url: String?
  ) {
    url?.let {
      view.loadUrl(it)
    }
  }

}