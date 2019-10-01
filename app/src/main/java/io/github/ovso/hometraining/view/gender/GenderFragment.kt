package io.github.ovso.hometraining.view.gender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.GenderFragmentBinding
import io.reactivex.Observable
import timber.log.Timber

class GenderFragment : Fragment() {

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
  }

  private fun setupViewpager() {
    val adapter = GenderAdapter(childFragmentManager).apply {
      //            items = getAdapterItem()
    }

    getAdapterItem()
  }

  private fun getAdapterItem(): MutableList<GenderAdapter.GenderAdapterItem>? {
    val tabsOfMale = context?.resources?.getStringArray(R.array.tabs_male)
        ?.toMutableList()
    val items = mutableListOf<GenderAdapter.GenderAdapterItem>()
    tabsOfMale?.let { it ->
      Observable.fromIterable(it)
          .subscribe {
            Timber.d(it)
          }
    }

    return null
  }
}