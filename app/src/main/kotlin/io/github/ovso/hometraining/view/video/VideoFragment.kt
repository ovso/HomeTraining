package io.github.ovso.hometraining.view.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.VideoFragmentBinding
import kotlinx.android.synthetic.main.video_fragment.rv_video
import org.jetbrains.anko.doAsync

class VideoFragment : Fragment() {

  companion object {
    fun newInstance() = VideoFragment()
  }

  private val viewModel by lazy {
    ViewModelProviders.of(this)
        .get(VideoViewModel::class.java)
  }

  private val adapter by lazy {
    VideoAdapter()
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
    setupRv()
    doAsync {

    }
  }

  private fun setupRv() {
    with(rv_video) {
      this.adapter = this@VideoFragment.adapter
    }
  }
}
