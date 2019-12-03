package io.github.ovso.hometraining.view.ui.player

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ActivityPlayerBinding
import io.github.ovso.hometraining.view.base.DataBindingActivity2
import kotlinx.android.synthetic.main.activity_player.you_tube_player_view

class PlayerActivity : DataBindingActivity2<ActivityPlayerBinding>(
    layoutResId = R.layout.activity_player,
    viewModelCls = PlayerViewModel::class.java
) {

  private val viewModel by lazy {
    ViewModelProvider(this)[PlayerViewModel::class.java]
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    youtube_player.enterFullScreen()

    observe()
  }

  private fun observe() {
    viewModel.videoIdLive.observe(this, Observer {
      you_tube_player_view.play("m2SZ6RV_J7I")
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

