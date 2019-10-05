package io.github.ovso.hometraining.view.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.VideoFragmentBinding

class VideoFragment : Fragment() {

  companion object {
    fun newInstance() = VideoFragment()
  }

  private val viewModel by lazy {
    ViewModelProviders.of(this)
        .get(VideoViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ) = generateBinding(inflater, container).root

  private fun generateBinding(
    inflater: LayoutInflater,
    container: ViewGroup?
  ) =
    DataBindingUtil.inflate<VideoFragmentBinding>(
        inflater,
        R.layout.video_fragment,
        container,
        false
    ).apply {
      this.viewModel = this@VideoFragment.viewModel
    }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

  }
}
