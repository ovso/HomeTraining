package io.github.ovso.hometraining.view.ui.male

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import io.github.ovso.hometraining.BR
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentMaleBinding
import io.github.ovso.hometraining.view.base.DataBindingFragment

class MaleFragment : DataBindingFragment<FragmentMaleBinding, MaleViewModel>() {

  companion object {
    val TAG: String = MaleFragment::class.java.simpleName
    fun newInstance() = MaleFragment()
  }

  private val viewModel by lazy {
    ViewModelProvider(this)[MaleViewModel::class.java]
  }


  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    observeLiveData()
  }

  private fun observeLiveData() {

  }

  override fun getLayoutId() = R.layout.fragment_male

  override fun getVariableValue() = viewModel

  override fun getVariableId() = BR.viewModel

}