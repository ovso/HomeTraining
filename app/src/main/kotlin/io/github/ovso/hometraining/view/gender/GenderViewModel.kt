package io.github.ovso.hometraining.view.gender

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.utils.SchedulerProvider
import io.github.ovso.hometraining.view.gender.GenderAdapter.GenderAdapterItem
import io.github.ovso.hometraining.view.video.VideoFragment
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class GenderViewModel(app: Application) : AndroidViewModel(app) {
  private val compositeDisposable by lazy { CompositeDisposable() }
  val initForTabsAndPager = MutableLiveData<MutableList<GenderAdapterItem>>()
  var type = 0
  fun fechList() {
    val items = mutableListOf<GenderAdapterItem>()
    compositeDisposable.add(
        Flowable.fromIterable(toList()).map {
          items.add(GenderAdapterItem(VideoFragment.newInstance(), it))
              .apply { }
        }.subscribeOn(SchedulerProvider.io(), true)
            .observeOn(SchedulerProvider.ui())
            .subscribeBy(onComplete = {
              Timber.d("onComplete")
              initForTabsAndPager.value = items
            }, onNext = {
              Timber.d("onNext")
            }, onError = {
              Timber.d("onError")
            })
    )
  }

  fun toList() = when (type) {
    0 -> ResourceProvider.getStringArray(R.array.tabs_title_male)
        .toMutableList()
    else -> ResourceProvider.getStringArray(R.array.tabs_title_female)
        .toMutableList()
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}
