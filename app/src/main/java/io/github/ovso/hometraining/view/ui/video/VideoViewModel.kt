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

  val itemsObField = ObservableField<JsonArray>()
  private val searchRequest by lazy { SearchRequest() }
  val errorDialogLive = MutableLiveData<Throwable>()

  private lateinit var titleAndQuery: TitleAndQuery
  val titleOb = ObservableField<String>()
  var query:String? = null

  init {
    addDisposable(
        RxBusBehavior.toObservable().subscribe {
          if (it is TitleAndQuery) {
            titleOb.set(it.title)
            query = it.query
            Timber.d("title = ${it.title}, query = ${it.query}")
            reqSearch()
          }
        }
    )
  }

  private fun reqSearch() {
    Timber.d("reqSearch query = $query")
    val disposable =
      searchRequest.search(
          query ?: ResourceProvider.getString(R.string.main_nav_title_male)
      )
          .doOnError {
            Timber.e(it)
          }
          .subscribeOn(SchedulerProvider.io())
          .observeOn(SchedulerProvider.ui())
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