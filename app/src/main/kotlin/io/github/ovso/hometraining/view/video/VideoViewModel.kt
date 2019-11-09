package io.github.ovso.hometraining.view.video

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import io.github.ovso.hometraining.data.api.SearchRequest
import io.github.ovso.hometraining.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class VideoViewModel : ViewModel() {
  var items = ObservableArrayList<Any>()
  private val compositeDisposable by lazy { CompositeDisposable() }
  private val searchRequest by lazy { SearchRequest() }

  init {
    val subscribeBy = searchRequest.search("여자 복근")
        .subscribeOn(SchedulerProvider.io())
        .subscribeBy {
          Timber.d("$it")
        }

    compositeDisposable.add(subscribeBy)
  }

  override fun onCleared() {
    compositeDisposable.clear()
  }
}
