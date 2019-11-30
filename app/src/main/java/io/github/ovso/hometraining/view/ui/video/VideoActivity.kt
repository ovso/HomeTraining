package io.github.ovso.hometraining.view.ui.video

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import io.github.ovso.hometraining.BR
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ActivityVideoBinding
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.view.base.DataBindingActivity

class VideoActivity : DataBindingActivity<ActivityVideoBinding, VideoViewModel>() {

  private val viewModel by lazy {
    ViewModelProvider(this)[VideoViewModel::class.java]
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.title = ResourceProvider.getString(R.string.video_title)
  }

  override fun getLayoutId() = R.layout.activity_video

  override fun getVariableValue() = viewModel

  override fun getVariableId() = BR.viewModel

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    finish()
    return super.onOptionsItemSelected(item)
  }
}
