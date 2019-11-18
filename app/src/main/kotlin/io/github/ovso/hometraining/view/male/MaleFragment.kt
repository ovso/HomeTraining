package io.github.ovso.hometraining.view.male

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentMainBinding
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_male.tabs_gender
import kotlinx.android.synthetic.main.fragment_male.viewpager_main_fragment

class MaleFragment : Fragment() {

  private val compositeDisposable = CompositeDisposable()

  companion object {
    val TAG: String = MaleFragment::class.java.simpleName
    fun newInstance() = MaleFragment()
  }

  private val viewModel by lazy {
    ViewModelProviders.of(this)
        .get(MaleFragmentViewModel::class.java)
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
    DataBindingUtil.inflate<FragmentMainBinding>(
        inflater,
        R.layout.fragment_male,
        container,
        false
    ).apply {
      this.viewModel = this@MaleFragment.viewModel
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
      setupWithViewPager(viewpager_main_fragment)
    }
    with(viewpager_main_fragment) {
      adapter = FemaleFragmentAdapter(childFragmentManager)
    }
  }

  override fun onDetach() {
    super.onDetach()
    compositeDisposable.clear()
  }

}