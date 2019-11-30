package io.github.ovso.hometraining.view.ui.pop

import androidx.lifecycle.ViewModelProvider
import io.github.ovso.hometraining.BR
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentPopularBinding
import io.github.ovso.hometraining.view.base.DataBindingFragment

class PopularFragment : DataBindingFragment<FragmentPopularBinding, PopularViewModel>() {

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
}