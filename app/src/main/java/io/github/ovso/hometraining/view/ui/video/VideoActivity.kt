package io.github.ovso.hometraining.view.ui.video

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import io.github.ovso.hometraining.BR
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ActivityVideoBinding
import io.github.ovso.hometraining.view.base.DataBindingActivity

class VideoActivity : DataBindingActivity<ActivityVideoBinding, VideoViewModel>() {

  private val viewModel by lazy {
    ViewModelProvider(this)[VideoViewModel::class.java]
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun getLayoutId() = R.layout.activity_video

  override fun getVariableValue() = viewModel

  override fun getVariableId() = BR.viewModel
}
