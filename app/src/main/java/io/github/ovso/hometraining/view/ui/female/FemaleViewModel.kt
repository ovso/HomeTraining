package io.github.ovso.hometraining.view.ui.female

import androidx.lifecycle.MutableLiveData
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.utils.SchedulerProvider
import io.github.ovso.hometraining.view.base.DisposableViewModel
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber

class FemaleViewModel : DisposableViewModel() {
  val itemsLive = MutableLiveData<MutableList<FemaleItem>>()

  init {
    fetchList()
  }

  private fun fetchList() {
    fun onSuccess(_items: MutableList<FemaleItem>) {
      itemsLive.value = _items
    }

    fun onFailure(t: Throwable) {
      Timber.e(t)
    }

    val titles = ResourceProvider.getStringArray(R.array.tabs_title_female)
        .toMutableList()
    val queries = ResourceProvider.getStringArray(R.array.queries_female)
    Observable.fromIterable(titles)
        .map { title ->
          val index = titles.indexOf(title)
          val query = queries[index]
          FemaleItem(title, query)
        }
        .toList()
        .subscribeOn(SchedulerProvider.io())
        .observeOn(SchedulerProvider.ui())
        .subscribe(::onSuccess, ::onFailure)

  }

}