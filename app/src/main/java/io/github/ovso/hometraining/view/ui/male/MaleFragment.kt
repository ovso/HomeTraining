package io.github.ovso.hometraining.view.ui.male

import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentMaleBinding
import io.github.ovso.hometraining.view.base.DataBindingFragment

class MaleFragment : DataBindingFragment<FragmentMaleBinding>(
    layoutRes = R.layout.fragment_male,
    modelClass = MaleViewModel::class.java
) {

  companion object {
    val TAG: String = MaleFragment::class.java.simpleName
    fun newInstance() = MaleFragment()
  }

}