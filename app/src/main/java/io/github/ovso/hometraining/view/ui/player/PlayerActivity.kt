package io.github.ovso.hometraining.view.ui.player

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ActivityPlayerBinding
import io.github.ovso.hometraining.view.base.DataBindingActivity2
import kotlinx.android.synthetic.main.activity_player.btn_web_back
import kotlinx.android.synthetic.main.activity_player.btn_web_forward
import kotlinx.android.synthetic.main.activity_player.btn_web_share
import kotlinx.android.synthetic.main.activity_player.wv_player

@SuppressLint("SetJavaScriptEnabled")
class PlayerActivity : DataBindingActivity2<ActivityPlayerBinding>(
    layoutResId = R.layout.activity_player,
    viewModelCls = PlayerViewModel::class.java
) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupWebView()
    addEvent()
  }

  private fun addEvent() {
    btn_web_back.setOnClickListener { wv_player.goBack() }
    btn_web_forward.setOnClickListener { wv_player.goForward() }
    btn_web_share.setOnClickListener {
      startActivity(
          Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, wv_player.url)
          }, "")
      )
    }
  }

  private fun setupWebView() {
    with(wv_player) {
      settings.javaScriptEnabled = true
      webViewClient = ViewClient()
      webChromeClient = ChromeClient()
    }
  }

  class ViewClient : WebViewClient()

  inner class ChromeClient :
      WebChromeClient() {
    override fun onProgressChanged(
        view: WebView?,
        newProgress: Int
    ) {
      super.onProgressChanged(view, newProgress)
      if (newProgress == 100) {
        view?.let {
          binding?.viewModel?.canGoBackOb?.set(it.canGoBack())
          binding?.viewModel?.canGoForwardOb?.set(it.canGoForward())
        }
      }
    }
  }

  override fun onDestroy() {
    wv_player.destroy()
    super.onDestroy()
  }

  override fun onBackPressed() {
    if (wv_player.canGoBack()) {
      wv_player.goBack()
    } else {
      super.onBackPressed()
    }
  }
}
