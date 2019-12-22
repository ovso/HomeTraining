package io.github.ovso.hometraining.view.ui.video

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ActivityVideoBinding
import io.github.ovso.hometraining.view.base.DataBindingActivity2
import kotlinx.android.synthetic.main.activity_video.rv_video
import kotlinx.android.synthetic.main.activity_video.toolbar
import org.koin.android.ext.android.inject

class VideoActivity : DataBindingActivity2<ActivityVideoBinding>(
    layoutResId = R.layout.activity_video,
    viewModelCls = VideoViewModel::class.java
) {

  private val adapter: VideoAdapter by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    setupRv()
    observe()
  }

  private fun observe() {
    val owner = this
    binding.viewModel?.itemsLive?.observe(owner, Observer {
      adapter.addAll(it)
    })
  }

  private fun setupRv() {
    rv_video.adapter = adapter
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    finish()
    return super.onOptionsItemSelected(item)
  }
}
