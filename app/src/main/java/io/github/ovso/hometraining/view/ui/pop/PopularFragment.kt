package io.github.ovso.hometraining.view.ui.pop

import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentPopularBinding
import io.github.ovso.hometraining.view.base.DataBindingFragment

class PopularFragment : DataBindingFragment<FragmentPopularBinding>(
    layoutRes = R.layout.fragment_popular,
    modelClass = PopularViewModel::class.java
) {

  companion object {
    val TAG: String = PopularFragment::class.java.simpleName
    fun newInstance() = PopularFragment()
  }
}