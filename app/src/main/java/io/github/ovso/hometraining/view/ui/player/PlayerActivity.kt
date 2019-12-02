package io.github.ovso.hometraining.view.ui.player

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants.PlayerState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.uber.autodispose.android.lifecycle.autoDispose
import io.github.ovso.hometraining.BR
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ActivityPlayerBinding
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.utils.RxBusBehavior.VideoId
import io.github.ovso.hometraining.view.base.DataBindingActivity
import kotlinx.android.synthetic.main.activity_player.*
import timber.log.Timber

class PlayerActivity : DataBindingActivity<ActivityPlayerBinding, PlayerViewModel>() {

  private val viewModel by lazy {
    ViewModelProvider(this)[PlayerViewModel::class.java]
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    youtube_player.enterFullScreen()
    RxBusBehavior.toObservable()
        .autoDispose(this)
        .subscribe {
          if (it is VideoId) {
            youtube_player.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
              override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo(it.id, 0F)
              }

              override fun onError(
                youTubePlayer: YouTubePlayer,
                error: PlayerConstants.PlayerError
              ) {
                Timber.d("error name = ${error.name}")
              }

              override fun onVideoId(
                youTubePlayer: YouTubePlayer,
                videoId: String
              ) {
                Timber.d("onVideoId = $videoId")
              }

              override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerState
              ) {
                Timber.d("onStateChange = $state")
              }
            })
          }
        }

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

