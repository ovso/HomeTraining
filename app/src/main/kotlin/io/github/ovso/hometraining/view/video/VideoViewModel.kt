package io.github.ovso.hometraining.view.video

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.google.gson.JsonArray
import io.github.ovso.hometraining.data.api.SearchRequest
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class VideoViewModel : ViewModel() {
  val itemsObField = ObservableField<JsonArray>()
  var title: String? = null
  private val compositeDisposable by lazy { CompositeDisposable() }
  private val searchRequest by lazy { SearchRequest() }

  private val viewModelJob = Job()
  private val uiScope by lazy {
    CoroutineScope(Dispatchers.Main + viewModelJob)
  }

  init {
/*
    val subscribeBy = searchRequest.search("여자 복근")
        .subscribeOn(SchedulerProvider.io())
        .subscribeBy {
          Timber.d("$it")
        }

    compositeDisposable.add(subscribeBy)
*/

    uiScope.launch {
      val searchCoroutine = searchRequest.searchCoroutine(title ?: "")
      val body = searchCoroutine.body()
      Timber.d(searchCoroutine.errorBody().toString())
      body?.asJsonObject?.get("items")
          ?.asJsonArray?.let {
        itemsObField.set(it)
      }
    }
  }

  override fun onCleared() {
    compositeDisposable.clear()
    viewModelJob.cancel()
  }
}
