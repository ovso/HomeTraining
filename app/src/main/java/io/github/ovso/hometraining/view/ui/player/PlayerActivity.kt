package io.github.ovso.hometraining.view.ui.player

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ActivityPlayerBinding
import io.github.ovso.hometraining.view.base.DataBindingActivity2
import kotlinx.android.synthetic.main.activity_player.wv_player

private const val BASE_URL_YOUTUBE = "https://www.youtube.com/watch?v="

class PlayerActivity : DataBindingActivity2<ActivityPlayerBinding>(
    layoutResId = R.layout.activity_player,
    viewModelCls = PlayerViewModel::class.java
) {

  private val viewModel by lazy {
    ViewModelProvider(this)[PlayerViewModel::class.java]
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupWebView()
    observe()
  }

  @SuppressLint("SetJavaScriptEnabled")
  private fun setupWebView() {
    wv_player.settings.apply {
      javaScriptEnabled = true
    }
    with(wv_player) {
      webViewClient = WebViewClient()
      webChromeClient = WebChromeClient()
    }
  }

  private fun observe() {
    viewModel.videoIdLive.observe(this, Observer {
      wv_player.loadUrl("$BASE_URL_YOUTUBE$it")
    })
  }
}
//AIzaSyCjXfwsCDjTWElpjFWdgr1ZMBBQGjsnS60
/*
  private fun enableFullscreen() {
    fullscreen_content.apply {
      systemUiVisibility =
        SYSTEM_UI_FLAG_LOW_PROFILE or
            SYSTEM_UI_FLAG_FULLSCREEN or
            SYSTEM_UI_FLAG_LAYOUT_STABLE or
            SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
            SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
  }

  private fun disableFullscreen() {
    supportActionBar?.show()
    fullscreen_content.apply {
      systemUiVisibility =
        SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }
  }
*/

