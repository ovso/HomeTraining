package io.github.ovso.hometraining.view.ui.female

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import io.github.ovso.hometraining.BR
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentFemaleBinding
import io.github.ovso.hometraining.view.base.DataBindingFragment

class FemaleFragment : DataBindingFragment<FragmentFemaleBinding, FemaleViewModel>() {

  companion object {
    val TAG: String = FemaleFragment::class.java.simpleName
    fun newInstance() = FemaleFragment()
  }

  private val viewModel by lazy {
    ViewModelProvider(this)[FemaleViewModel::class.java]
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    observeLiveData()
  }

  private fun observeLiveData() {

  }

  override fun getLayoutId() = R.layout.fragment_female

  override fun getVariableId() = BR.viewModel
  override fun getVariableValue() = viewModel

}