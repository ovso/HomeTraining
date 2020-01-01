package io.github.ovso.hometraining.view.ui.male

import androidx.lifecycle.MutableLiveData
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.utils.SchedulerProvider
import io.github.ovso.hometraining.view.base.DisposableViewModel
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber

class MaleViewModel : DisposableViewModel() {
  val itemsLive = MutableLiveData<MutableList<MaleItem>>()

  init {
    fetchList()
  }

  private fun fetchList() {
    fun onSuccess(_items: MutableList<MaleItem>) {
      itemsLive.value = _items
    }

    fun onFailure(t: Throwable) {
      Timber.e(t)
    }

    val titles = ResourceProvider.getStringArray(R.array.tabs_title_male)
        .toMutableList()
    val queries = ResourceProvider.getStringArray(R.array.queries_male)
    Observable.fromIterable(titles)
        .map { title ->
          val index = titles.indexOf(title)
          val query = queries[index]
          MaleItem(title, query)
        }
        .toList()
        .subscribeOn(SchedulerProvider.io())
        .observeOn(SchedulerProvider.ui())
        .subscribe(::onSuccess, ::onFailure)

  }

}