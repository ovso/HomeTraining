package io.github.ovso.hometraining.view.ui.player

import android.annotation.SuppressLint
import android.os.Bundle
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ActivityPlayerBinding
import io.github.ovso.hometraining.view.base.DataBindingActivity2
import kotlinx.android.synthetic.main.activity_player.wv_player

@SuppressLint("SetJavaScriptEnabled")
class PlayerActivity : DataBindingActivity2<ActivityPlayerBinding>(
    layoutResId = R.layout.activity_player,
    viewModelCls = PlayerViewModel::class.java
) {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    with(wv_player) {
      settings.javaScriptEnabled = true
      webViewClient = android.webkit.WebViewClient()
      webChromeClient = android.webkit.WebChromeClient()
    }
  }
}