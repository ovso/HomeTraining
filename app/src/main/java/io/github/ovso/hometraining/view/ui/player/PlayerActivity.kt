package io.github.ovso.hometraining.view.ui.player

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.Provider
import com.google.android.youtube.player.YouTubePlayerFragment
import io.github.ovso.hometraining.BR
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ActivityPlayerBinding
import io.github.ovso.hometraining.view.base.DataBindingActivity
import kotlinx.android.synthetic.main.activity_player.fragment_player_youtube
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber

class PlayerActivity : DataBindingActivity<ActivityPlayerBinding, PlayerViewModel>() {

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
      Timber.d("fragment_player_youtube = $fragment_player_youtube")
      if (fragment_player_youtube is YouTubePlayerFragment) {
        (fragment_player_youtube as YouTubePlayerFragment).initialize(
            "AIzaSyCjXfwsCDjTWElpjFWdgr1ZMBBQGjsnS60",
            object : YouTubePlayer.OnInitializedListener {
              override fun onInitializationSuccess(
                provider: Provider?,
                player: YouTubePlayer?,
                p2: Boolean
              ) {
                Timber.d("onInitializationSuccess")
                player?.loadVideo(it)
              }

              override fun onInitializationFailure(
                provider: Provider?,
                result: YouTubeInitializationResult?
              ) {
                Timber.d("onInitializationFailure = ${result?.name}")
              }
            })

      }
    })
  }

  override fun getLayoutId() = R.layout.activity_player
  override fun getVariableValue() = viewModel

  override fun getVariableId() = BR.viewModel
}

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

