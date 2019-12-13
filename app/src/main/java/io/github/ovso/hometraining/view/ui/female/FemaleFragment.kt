package io.github.ovso.hometraining.view.ui.female

import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentFemaleBinding
import io.github.ovso.hometraining.view.base.DataBindingFragment

class FemaleFragment : DataBindingFragment<FragmentFemaleBinding>(
    layoutRes = R.layout.fragment_female,
    modelClass = FemaleViewModel::class.java
) {

  companion object {
    val TAG: String = FemaleFragment::class.java.simpleName
    fun newInstance() = FemaleFragment()
  }

}