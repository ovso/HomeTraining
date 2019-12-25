package io.github.ovso.hometraining.view.ui.female

import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentFemaleBinding
import io.github.ovso.hometraining.view.base.DataBindingFragment2

class FemaleFragment : DataBindingFragment2<FragmentFemaleBinding>(
    layoutResId = R.layout.fragment_female,
    viewModelCls = FemaleViewModel::class.java
) {

  companion object {
    val TAG: String = FemaleFragment::class.java.simpleName
    fun newInstance() = FemaleFragment()
  }

}