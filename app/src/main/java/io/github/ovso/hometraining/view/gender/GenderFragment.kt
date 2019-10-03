package io.github.ovso.hometraining.view.gender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProviders
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.GenderFragmentBinding
import io.github.ovso.hometraining.view.gender.GenderAdapter.GenderAdapterItem
import io.github.ovso.hometraining.view.video.VideoFragment
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.gender_fragment.*

class GenderFragment : Fragment() {

  private val compositeDisposable = CompositeDisposable()
  private val scopeProvider by lazy {
    AndroidLifecycleScopeProvider.from(
        this, Lifecycle.Event.ON_DESTROY
    )
  }

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
    val items = mutableListOf<GenderAdapterItem>()
    compositeDisposable.add(
        Flowable.fromIterable(
            context?.resources?.getStringArray(R.array.tabs_male)?.toMutableList()
        )
            .map {
              items.add(GenderAdapterItem(VideoFragment.newInstance(), it))
              items
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onComplete = {
              tabs_gender.setupWithViewPager(viewpager_gender)
              viewpager_gender.adapter = GenderAdapter(childFragmentManager).apply {
                this.items = items.toMutableList()
              }
            })
    )

/*
        .subscribe {
          Timber.d("subscribe Consumer size = ${it.size}")
        }
*/

  }

  override fun onDetach() {
    super.onDetach()
    compositeDisposable.clear()
  }

}