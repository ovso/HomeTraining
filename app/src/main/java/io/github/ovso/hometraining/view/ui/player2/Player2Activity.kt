package io.github.ovso.hometraining.view.ui.player2

import android.content.res.Configuration
import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.gms.ads.AdRequest
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ActivityPlayer2Binding
import io.github.ovso.hometraining.exts.gone
import io.github.ovso.hometraining.exts.visible
import io.github.ovso.hometraining.view.base.DataBindingActivity2
import kotlinx.android.synthetic.main.activity_player2.*
import kotlinx.android.synthetic.main.layout_ads_banner.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class Player2Activity : DataBindingActivity2<ActivityPlayer2Binding>(
    layoutResId = R.layout.activity_player2,
    viewModelCls = Player2ViewModel::class.java
) {

    private val adRequest: AdRequest by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(youtube_player_view)
        val v = binding.viewModel
        v?.videoIdLive?.observe(this, Observer {
            youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.loadOrCueVideo(lifecycle, it, binding.viewModel!!.second)
                    Timber.d("loadOrCueVideo")
                }

                override fun onCurrentSecond(
                    youTubePlayer: YouTubePlayer,
                    second: Float
                ) {
                    super.onCurrentSecond(youTubePlayer, second)
                    binding.viewModel?.second = second
                }
            })
        })
        setupAds()
    }

    private fun setupAds() {
        resources.configuration.run {
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                player_ads_banner_container.visible()
                all_ads_banner.loadAd(adRequest)
            } else {
                player_ads_banner_container.gone()
            }
        }
    }
}
