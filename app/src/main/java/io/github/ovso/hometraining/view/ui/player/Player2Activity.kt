package io.github.ovso.hometraining.view.ui.player

import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import io.github.ovso.hometraining.R

class Player2Activity : YouTubeBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player2)

        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.you_tube_player_view)
/*
        youTubePlayerView.initialize("AIzaSyA4pdIQO-74kZv7MLpPZs13oEYq2w5ki4E", object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                b: Boolean
            ) {
                player?.cueVideo("m2SZ6RV_J7I")
            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider?,
                result: YouTubeInitializationResult?
            ) {
            }
        })
*/
    }

    companion object {
        private const val VIDEO_ID = "m2SZ6RV_J7I"
    }

}