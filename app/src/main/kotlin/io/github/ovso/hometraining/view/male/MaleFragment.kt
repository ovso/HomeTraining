package io.github.ovso.hometraining.view.male

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentMaleBinding
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_male.*

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
    DataBindingUtil.inflate<FragmentMaleBinding>(
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
      setupWithViewPager(viewpager_male_fragment)
    }
    with(viewpager_male_fragment) {
      offscreenPageLimit = 1
      adapter = MaleFragmentAdapter(childFragmentManager)
    }
  }

  override fun onDetach() {
    super.onDetach()
    compositeDisposable.clear()
  }

}