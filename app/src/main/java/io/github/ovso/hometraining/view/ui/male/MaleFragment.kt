package io.github.ovso.hometraining.view.ui.male

import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentMaleBinding
import io.github.ovso.hometraining.view.base.DataBindingFragment2

class MaleFragment : DataBindingFragment2<FragmentMaleBinding>(
    layoutResId = R.layout.fragment_male,
    viewModelCls = MaleViewModel::class.java
) {

  companion object {
    val TAG: String = MaleFragment::class.java.simpleName
    fun newInstance() = MaleFragment()
  }
}