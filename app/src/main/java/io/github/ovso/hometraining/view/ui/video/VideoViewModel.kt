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
import io.github.ovso.hometraining.utils.prefs.KeyPreferences
import io.github.ovso.hometraining.view.base.DisposableViewModel
import retrofit2.HttpException
import timber.log.Timber

class VideoViewModel : DisposableViewModel() {

  private val searchRequest by lazy {
    SearchRequest()
  }
  val itemsLive = MutableLiveData<JsonArray>()

  val titleOb = ObservableField<String>()
  private var query: String? = null

  init {
    RxBusBehavior.toObservable()
        .subscribe {
          if (it is VideoData) {
            titleOb.set(it.title)
            query = it.query
            reqSearch()
          }
        }
        .apply {
          addDisposable(this)
        }
  }

  private fun reqSearch() {

    fun getQueryMap(): Map<String, Any> {
      val q = query ?: ResourceProvider.getString(R.string.app_name)
      return hashMapOf(
          "q" to q,
          "maxResults" to 50,
          "order" to "viewCount",
          "type" to "video",
          "videoSyndicated" to "any",
          "key" to ResourceProvider.getStringArray(R.array.keys)[KeyPreferences.index],
          "part" to "snippet",
          "fields" to "items(id,snippet(title,thumbnails(medium)))"
      )
    }

    searchRequest.api()
        .search(getQueryMap())
        .subscribeOn(SchedulerProvider.io())
        .observeOn(SchedulerProvider.ui())
        .doOnSubscribe { isLoading.set(true) }
        .doOnSuccess { isLoading.set(false) }
        .doOnError { isLoading.set(false) }
        .subscribe(::onSuccess, ::onError)
        .apply {
          addDisposable(this)
        }
  }

  private fun onSuccess(it: JsonElement) {
    itemsLive.value = it.asJsonObject["items"].asJsonArray
  }

  private fun onError(it: Throwable) {
    Timber.e(it)
    Timber.d((it as? HttpException)?.response()?.errorBody()?.string())
  }

  data class RxBusVideoTitle(var text: String)
  data class VideoData(
    val title: String,
    val query: String
  )
}