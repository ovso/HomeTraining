package io.github.ovso.hometraining.view.ui.pop

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.github.ovso.hometraining.BR
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentPopularBinding
import io.github.ovso.hometraining.view.base.DataBindingFragment
import io.github.ovso.hometraining.view.ui.video.VideoAdapter
import kotlinx.android.synthetic.main.fragment_popular.rv_popular
import org.koin.android.ext.android.inject

class PopularFragment : DataBindingFragment<FragmentPopularBinding, PopularViewModel>() {

  private val adapter: VideoAdapter by inject()
  private val viewModel by lazy {
    ViewModelProvider(this)[PopularViewModel::class.java]
  }

  companion object {
    val TAG: String = PopularFragment::class.java.simpleName
    fun newInstance() = PopularFragment()
  }

  override fun getLayoutId() = R.layout.fragment_popular

  override fun getVariableValue() = viewModel

  override fun getVariableId() = BR.viewModel

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setupRv()
    observe()
  }

  private fun setupRv() {
    rv_popular.adapter = adapter
  }

  private fun observe() {
    val owner = viewLifecycleOwner
    viewModel.itemsLive.observe(owner, Observer {
      adapter.addAll(_items = it)
    })
  }

}