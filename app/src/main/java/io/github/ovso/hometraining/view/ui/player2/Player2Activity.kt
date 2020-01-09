package io.github.ovso.hometraining.view.ui.player2

import android.content.res.Configuration
import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ActivityPlayer2Binding
import io.github.ovso.hometraining.view.base.DataBindingActivity2
import kotlinx.android.synthetic.main.activity_player2.youtube_player_view
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.Orientation
import org.jetbrains.anko.toast
import timber.log.Timber


class Player2Activity : DataBindingActivity2<ActivityPlayer2Binding>(
    layoutResId = R.layout.activity_player2,
    viewModelCls = Player2ViewModel::class.java
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val v = binding.viewModel
        v?.videoIdLive?.observe(this, Observer {
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
                    binding.viewModel?.second = second
                }
            })
        })
        awareOrientation()
        setupAds()
    }

    private fun awareOrientation() {
        val orientation = resources.configuration.orientation
        if (Configuration.ORIENTATION_LANDSCAPE == orientation) { //Do SomeThing; // Landscape
            toast("ORIENTATION_LANDSCAPE")
        } else { //Do SomeThing;  // Portrait
            toast("ORIENTATION_PORTRAIT")
        }
    }

    private fun setupAds() {
        val adView = AdView(this)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        resources.configuration.run {
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                ad_container.addView(adView)
            }
        }

    }

}
