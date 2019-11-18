package io.github.ovso.hometraining.view.player

import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
import android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.View.SYSTEM_UI_FLAG_LOW_PROFILE
import androidx.appcompat.app.AppCompatActivity
import io.github.ovso.hometraining.R
import kotlinx.android.synthetic.main.activity_player.fullscreen_content

class PlayerActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_player)
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    enableFullscreen()
  }

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
}
