package io.github.ovso.hometraining.view.female

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentFemaleBinding
import io.github.ovso.hometraining.view.male.FemaleFragmentAdapter
import io.github.ovso.hometraining.view.male.FemaleFragmentViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_female.viewpager_female_fragment
import kotlinx.android.synthetic.main.fragment_male.tabs_gender

class FemaleFragment : Fragment() {

  private val compositeDisposable = CompositeDisposable()

  companion object {
    val TAG: String = FemaleFragment::class.java.simpleName
    fun newInstance() = FemaleFragment()
  }

  private val viewModel by lazy {
    ViewModelProviders.of(this)
        .get(FemaleFragmentViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ) = generateBinding(inflater, container).root

  private fun generateBinding(
    inflater: LayoutInflater,
    container: ViewGroup?
  ) =
    DataBindingUtil.inflate<FragmentFemaleBinding>(
        inflater,
        R.layout.fragment_female,
        container,
        false
    ).apply {
      this.viewModel = this@FemaleFragment.viewModel
    }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setupViewpager()
    observeLiveData()
  }

  private fun observeLiveData() {

  }

  private fun setupViewpager() {
    with(tabs_gender) {
      setupWithViewPager(viewpager_female_fragment)
    }
    with(viewpager_female_fragment) {
      adapter = FemaleFragmentAdapter(childFragmentManager)
    }
  }

  override fun onDetach() {
    super.onDetach()
    compositeDisposable.clear()
  }

}