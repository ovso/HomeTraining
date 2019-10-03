package io.github.ovso.hometraining.view.gender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.GenderFragmentBinding
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.gender_fragment.tabs_gender
import kotlinx.android.synthetic.main.gender_fragment.viewpager_gender

class GenderFragment : Fragment() {

  private val compositeDisposable = CompositeDisposable()
/*
  private val scopeProvider by lazy {
    AndroidLifecycleScopeProvider.from(
        this, Lifecycle.Event.ON_DESTROY
    )
  }
*/

  companion object {
    fun newInstance() = GenderFragment()
  }

  private val viewModel by lazy {
    ViewModelProviders.of(this)
        .get(GenderViewModel::class.java)
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
    DataBindingUtil.inflate<GenderFragmentBinding>(
        inflater,
        R.layout.gender_fragment,
        container,
        false
    ).apply {
      this.viewModel = this@GenderFragment.viewModel
    }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setupViewpager()
    observeLiveData()
    viewModel.fechList()
  }

  private fun observeLiveData() {
    viewModel.initForTabsAndPager.observe(this, Observer {
      with(viewpager_gender.adapter) {
        if (this is GenderAdapter) {
          with(this) {
            items = it
            notifyDataSetChanged()
          }
        }
      }
    })
  }

  private fun setupViewpager() {
    with(tabs_gender) {
      setupWithViewPager(viewpager_gender)
    }
    viewpager_gender.adapter = GenderAdapter(childFragmentManager)
  }

  override fun onDetach() {
    super.onDetach()
    compositeDisposable.clear()
  }
}