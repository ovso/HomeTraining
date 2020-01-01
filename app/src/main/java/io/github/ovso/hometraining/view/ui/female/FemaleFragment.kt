package io.github.ovso.hometraining.view.ui.female

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentFemaleBinding
import io.github.ovso.hometraining.view.base.DataBindingFragment2
import kotlinx.android.synthetic.main.fragment_female.rv_female
import org.koin.android.ext.android.inject

class FemaleFragment : DataBindingFragment2<FragmentFemaleBinding>(
    layoutResId = R.layout.fragment_female,
    viewModelCls = FemaleViewModel::class.java
) {

  private val adapter: FemaleAdapter by inject()

  companion object {
    val TAG: String = FemaleFragment::class.java.simpleName
    fun newInstance() = FemaleFragment()
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setupRv()
    observe()
  }

  private fun observe() {
    binding.viewModel?.itemsLive?.observe(viewLifecycleOwner, Observer {
      adapter.submitList(it)
    })
  }

  private fun setupRv() {
    rv_female.apply {
      addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
      this.adapter = this@FemaleFragment.adapter
    }
  }
}