package io.github.ovso.hometraining.view.gender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.GenderFragmentBinding
import io.github.ovso.hometraining.view.gender.GenderAdapter.GenderAdapterItem
import io.github.ovso.hometraining.view.video.VideoFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GenderFragment : Fragment() {

  private val scopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }

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
          .map {
            items.add(GenderAdapterItem(VideoFragment.newInstance(), it))
          }
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .autoDispose(scopeProvider)
          .subscribe()

    }

    return null
  }
}

/*

  implementation project(':android:autodispose-android')
  implementation project(':android:autodispose-androidx-lifecycle')
  implementation project(':autodispose')
  implementation project(':autodispose-lifecycle')
* */