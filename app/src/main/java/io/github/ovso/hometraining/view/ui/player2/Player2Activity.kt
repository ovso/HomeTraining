package io.github.ovso.hometraining.view.ui.player2

import android.os.Bundle
import androidx.lifecycle.Observer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ActivityPlayer2Binding
import io.github.ovso.hometraining.view.base.DataBindingActivity
import kotlinx.android.synthetic.main.activity_player2.youtube_player_view
import timber.log.Timber

class Player2Activity : DataBindingActivity<ActivityPlayer2Binding>(
    layoutResId = R.layout.activity_player2,
    viewModelCls = Player2ViewModel::class.java
) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding?.viewModel?.videoIdLive?.observe(this, Observer {
      lifecycle.addObserver(youtube_player_view)
      youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
          super.onReady(youTubePlayer)
          youTubePlayer.loadOrCueVideo(lifecycle, it, binding!!.viewModel!!.second)
          Timber.d("loadOrCueVideo")
        }

        override fun onCurrentSecond(
          youTubePlayer: YouTubePlayer,
          second: Float
        ) {
          super.onCurrentSecond(youTubePlayer, second)
          binding?.viewModel?.second = second
        }
      })
    })
  }
}