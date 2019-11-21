package io.github.ovso.hometraining.view.video

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.data.api.SearchRequest
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.utils.SchedulerProvider
import io.github.ovso.hometraining.view.base.DisposableViewModel
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class VideoViewModel : DisposableViewModel() {
  val itemsObField = ObservableField<JsonArray>()
  var query: String? = null
  private val searchRequest by lazy { SearchRequest() }
  val errorDialogLive = MutableLiveData<Throwable>()

  init {
    reqSearch()
  }

  private fun reqSearch() {
    val disposable =
      searchRequest.search(query ?: ResourceProvider.getString(R.string.main_nav_title_male))
          .subscribeOn(SchedulerProvider.io())
          .subscribeBy(
              onError = ::onError,
              onNext = ::onSuccess,
              onComplete = ::onComplete
          )
    addDispose(disposable)
  }

  private fun onError(throwable: Throwable) {
    Timber.d("throwable = ${throwable.message}")
    errorDialogLive.postValue(throwable)
  }

  private fun onSuccess(json: JsonElement?) {
    json?.let {
      itemsObField.set(it.asJsonObject["items"].asJsonArray)
    }
  }

  private fun onComplete() {
    Timber.d("onComplete()")
  }

}
