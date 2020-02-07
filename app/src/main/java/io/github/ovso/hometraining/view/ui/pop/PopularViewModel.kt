package io.github.ovso.hometraining.view.ui.pop

import androidx.lifecycle.MutableLiveData
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.data.api.SearchRequest
import io.github.ovso.hometraining.exts.plusAssign
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.utils.SchedulerProvider
import io.github.ovso.hometraining.view.base.DisposableViewModel
import io.github.ovso.hometraining.view.ui.video.model.VideoItem
import retrofit2.HttpException
import timber.log.Timber

class PopularViewModel : DisposableViewModel() {
    val items = MutableLiveData<List<VideoItem>>()
    private val searchRequest by lazy {
        SearchRequest()
    }

    init {
        reqSearch()
    }

    private fun reqSearch() {

        fun onSuccess(_items: List<VideoItem>) {
            items.value = _items
        }

        fun onError(it: Throwable) {
            Timber.e(it)
            Timber.d((it as? HttpException)?.response()?.errorBody()?.string())
        }

        fun getParams() = mapOf(
            "q" to ResourceProvider.getString(R.string.popular_query),
            "maxResults" to 50,
            "order" to "viewCount",
            "type" to "video",
            "videoSyndicated" to "any",
            "key" to ResourceProvider.getStringArray(R.array.keys).first(),
            "part" to "snippet",
            "fields" to "items(id,snippet(title,thumbnails(medium)))"
        )

        compositeDisposable += searchRequest.api()
            .search(getParams())
            .map {
                searchRequest.toVideoItems(it, 5)
            }
            .subscribeOn(SchedulerProvider.io())
            .observeOn(SchedulerProvider.ui())
            .doOnSubscribe { showLoading() }
            .doOnError { hideLoading() }
            .doFinally { hideLoading() }
            .subscribe(::onSuccess, ::onError)
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
