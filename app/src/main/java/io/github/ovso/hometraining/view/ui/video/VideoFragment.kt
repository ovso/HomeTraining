package io.github.ovso.hometraining.view.ui.video

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.ovso.hometraining.BR
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentVideoBinding
import io.github.ovso.hometraining.view.base.DataBindingDialogFragment
import kotlinx.android.synthetic.main.fragment_video.rv_video
import retrofit2.HttpException
import timber.log.Timber

class VideoFragment : DataBindingDialogFragment<FragmentVideoBinding, VideoViewModel>() {

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


  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setupRv()
    observe()
    fetchList()
  }

  private fun fetchList() {
  }

  private fun observe() {
    viewModel.errorDialogLive.observe(this, Observer {
      if (it is HttpException) {
        Timber.e(it.response()?.errorBody()?.string())
      }
      Timber.e("error msg = ${it.message}")
    })
  }

  private fun setupRv() {
    with(rv_video) {
      this.adapter = this@VideoFragment.adapter
    }
  }

  override fun getLayoutId() = R.layout.fragment_video
  override fun getVariableValue() = viewModel

  override fun getVariableId() = BR.viewModel
}
