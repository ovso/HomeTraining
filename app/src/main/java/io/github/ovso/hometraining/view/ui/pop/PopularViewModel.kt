package io.github.ovso.hometraining.view.ui.pop

import androidx.databinding.ObservableField
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.data.api.SearchRequest
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.utils.SchedulerProvider
import io.github.ovso.hometraining.utils.prefs.KeyPreferences
import io.github.ovso.hometraining.view.base.DisposableViewModel
import retrofit2.HttpException
import timber.log.Timber

class PopularViewModel : DisposableViewModel() {
  val items = ObservableField<JsonArray>()
  private val searchRequest2 by lazy {
    SearchRequest()
  }

  init {
    reqSearch2()
  }

  private fun reqSearch2() {

    searchRequest2.api()
        .search(getQueryMap())
        .subscribeOn(SchedulerProvider.io())
        .observeOn(SchedulerProvider.ui())
        .subscribe(::onSuccess, ::onError)
        .apply {
          addDisposable(this)
        }
  }

  private fun getQueryMap(): Map<String, Any> {
    return hashMapOf(
        "q" to ResourceProvider.getString(R.string.popular_query),
        "maxResults" to 50,
        "order" to "viewCount",
        "type" to "video",
        "videoSyndicated" to "any",
        "key" to ResourceProvider.getStringArray(R.array.keys)[KeyPreferences.index],
        "part" to "snippet",
        "fields" to "items(id,snippet(title,thumbnails(medium)))"
    )
  }

  private fun onSuccess(it: JsonElement) {
    items.set(it.asJsonObject["items"].asJsonArray)
  }

  private fun onError(it: Throwable) {
    Timber.e(it)
    Timber.d((it as? HttpException)?.response()?.errorBody()?.string())
  }

}

/*
  fun search(q: String): Single<JsonElement> {
    val qMap = hashMapOf(
        "q" to q,
        "maxResults" to 50,
        "order" to "viewCount",
        "type" to "video",
        "videoSyndicated" to "any",
        "key" to nextApiKey(),
        "part" to "snippet",
        "fields" to "items(id,snippet(title,thumbnails(medium)))"
    )
    return api.search(qMap)
  }
*/
