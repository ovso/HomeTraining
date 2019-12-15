package io.github.ovso.hometraining.view.ui.video

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.data.api.SearchRequest
import io.github.ovso.hometraining.data.api.SearchRequest2
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.utils.SchedulerProvider
import io.github.ovso.hometraining.view.base.AllViewHolder.TitleAndQuery
import io.github.ovso.hometraining.view.base.DisposableViewModel
import retrofit2.HttpException
import timber.log.Timber

class VideoViewModel : DisposableViewModel() {

  val items = ObservableField<JsonArray>()
  private val searchRequest by lazy { SearchRequest() }
  private val searchRequest2 by lazy {
    SearchRequest2()
  }

  val errorDialogLive = MutableLiveData<Throwable>()

  val titleOb = ObservableField<String>()
  private var query: String? = null

  init {
    addDisposable(
        RxBusBehavior.toObservable()
            .subscribe {
              if (it is TitleAndQuery) {
                titleOb.set(it.title)
                query = it.query
                Timber.d("title = ${it.title}, query = ${it.query}")
                reqSearch2()
              }
            }
    )
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
    val q = (query ?: ResourceProvider.getString(R.string.main_nav_title_male))

    fun getApiKey(): String {
      val keys =
        "AIzaSyA4pdIQO-74kZv7MLpPZs13oEYq2w5ki4E//AIzaSyCDlPMTU-TsKp8k7t6875jkAIRWrl2XCfE//AIzaSyCe8fJ3dw_8YzFq1L7X3Iip9Bs_KZ66bNM//AIzaSyBT2wy_F43ouGtgmNBmklik6qYHYFIVtbA"
      return keys.split("//")[0]
    }

    return hashMapOf(
        "q" to q,
        "maxResults" to 50,
        "order" to "viewCount",
        "type" to "video",
        "videoSyndicated" to "any",
        "key" to getApiKey(),
        "part" to "snippet",
        "fields" to "items(id,snippet(title,thumbnails(medium)))"
    )
  }

  private fun reqSearch() {
    val q = query ?: ResourceProvider.getString(R.string.main_nav_title_male)
    Timber.d("reqSearch q = $q")
    searchRequest.search(q)
        .subscribeOn(SchedulerProvider.io())
        .observeOn(SchedulerProvider.ui())
        .subscribe(::onSuccess, ::onError)
        .apply {
          addDisposable(this)
        }
  }

  private fun onSuccess(it: JsonElement) {
    items.set(it.asJsonObject["items"].asJsonArray)
  }

  private fun onError(it: Throwable) {
    Timber.e(it)
    Timber.d((it as? HttpException)?.response()?.errorBody()?.string())
  }
}