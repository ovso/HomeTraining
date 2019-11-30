package io.github.ovso.hometraining.view.ui.video

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.data.api.SearchRequest
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.utils.SchedulerProvider
import io.github.ovso.hometraining.view.base.AllViewHolder.TitleAndQuery
import io.github.ovso.hometraining.view.base.DisposableViewModel
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class VideoViewModel : DisposableViewModel() {

  private lateinit var titleAndQuery: TitleAndQuery
  val titleOb = ObservableField<String>()
  val queryOb = ObservableField<String>()

  init {
    addDisposable(
        RxBusBehavior.toObservable().subscribe {
          if (it is TitleAndQuery) {
            titleOb.set(it.title)
            queryOb.set(it.query)
            Timber.d("title = ${titleOb.get()}, query = ${queryOb.get()}")
          }
        }
    )
  }

  val itemsObField = ObservableField<JsonArray>()
  private val searchRequest by lazy { SearchRequest() }
  val errorDialogLive = MutableLiveData<Throwable>()

  fun reqSearch() {
    Timber.d("reqSearch query = ${queryOb.get()}")
    val disposable =
      searchRequest.search(
          queryOb.get() ?: ResourceProvider.getString(R.string.main_nav_title_male)
      )
          .subscribeOn(SchedulerProvider.io())
          .subscribeBy(
              onError = ::onError,
              onNext = ::onSuccess,
              onComplete = ::onComplete
          )
    addDisposable(disposable)
  }

  private fun onError(t: Throwable) {
    errorDialogLive.postValue(t)
  }

  private fun onSuccess(json: JsonElement) {
    Timber.d("onSuccess")
    itemsObField.set(json.asJsonObject["items"].asJsonArray)
  }

  private fun onComplete() {
    Timber.d("onComplete()")
  }

}