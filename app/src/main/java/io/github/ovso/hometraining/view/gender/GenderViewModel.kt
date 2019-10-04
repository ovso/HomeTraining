package io.github.ovso.hometraining.view.gender

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.view.gender.GenderAdapter.GenderAdapterItem
import io.github.ovso.hometraining.view.video.VideoFragment
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class GenderViewModel(private var app: Application) : AndroidViewModel(app) {
  private val compositeDisposable = CompositeDisposable()
  val initForTabsAndPager = MutableLiveData<MutableList<GenderAdapterItem>>()
  var type = 0
  fun fechList() {
    val items = mutableListOf<GenderAdapterItem>()
    compositeDisposable.add(
        Flowable.fromIterable(
            when (type) {
              0 -> app.resources.getStringArray(R.array.tabs_title_male)
                  .toMutableList()
              else -> app.resources.getStringArray(R.array.tabs_title_female)
                  .toMutableList()
            }
        ).map {
          items.add(GenderAdapterItem(VideoFragment.newInstance(), it))
          items
        }.subscribeOn(Schedulers.io(), true).observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onComplete = {
              Timber.d("onComplete")
              initForTabsAndPager.postValue(items)
            }, onNext = {
              Timber.d("onNext")
            }, onError = {
              Timber.d("onNext")
            })
    )
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}
