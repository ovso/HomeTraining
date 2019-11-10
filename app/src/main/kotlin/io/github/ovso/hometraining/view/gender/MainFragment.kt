package io.github.ovso.hometraining.view.gender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentMainBinding
import io.github.ovso.hometraining.view.main.BottomMenu
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_main.tabs_gender
import kotlinx.android.synthetic.main.fragment_main.viewpager_main_fragment

class MainFragment : Fragment() {

  private val compositeDisposable = CompositeDisposable()

  companion object {
    fun newInstance(menu: BottomMenu): MainFragment {
      return MainFragment().apply {
        arguments = bundleOf("menu" to menu)
      }
    }
  }

  private val viewModel by lazy {
    ViewModelProviders.of(this)
        .get(MainFragmentViewModel::class.java)
        .apply {
          typeLiveData.value = requireArguments().getSerializable("menu") as BottomMenu
        }
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
        R.layout.fragment_main,
        container,
        false
    ).apply {
      this.viewModel = this@MainFragment.viewModel
    }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setupViewpager()
    observeLiveData()
  }

  private fun observeLiveData() {
    viewModel.typeLiveData.observe(this, Observer {
      (viewpager_main_fragment.adapter as? MainFragmentAdapter)?.handleData(it)
    })
  }

  private fun setupViewpager() {
    with(tabs_gender) {
      setupWithViewPager(viewpager_main_fragment)
    }
    viewpager_main_fragment.adapter = MainFragmentAdapter(childFragmentManager)
  }

  override fun onDetach() {
    super.onDetach()
    compositeDisposable.clear()
  }
}