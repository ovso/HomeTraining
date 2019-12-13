package io.github.ovso.hometraining.view.ui.video

import android.os.Bundle
import android.view.MenuItem
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ActivityVideoBinding
import io.github.ovso.hometraining.view.base.DataBindingActivity2

class VideoActivity : DataBindingActivity2<ActivityVideoBinding>(
    layoutResId = R.layout.activity_video,
    viewModelCls = VideoViewModel::class.java
) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    finish()
    return super.onOptionsItemSelected(item)
  }
}
