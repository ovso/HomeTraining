package io.github.ovso.hometraining.view.ui.video

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.data.api.SearchRequest
import io.github.ovso.hometraining.exts.plusAssign
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.utils.SchedulerProvider
import io.github.ovso.hometraining.utils.prefs.KeyPreferences
import io.github.ovso.hometraining.view.base.DisposableViewModel
import io.github.ovso.hometraining.view.ui.video.model.VideoItem
import retrofit2.HttpException
import timber.log.Timber

class VideoViewModel : DisposableViewModel() {

    private val searchRequest by lazy {
        SearchRequest()
    }
    val items = MutableLiveData<List<VideoItem>>()

    val title = ObservableField<String>()
    private var query: String? = null

    init {
        compositeDisposable += RxBusBehavior.toObservable()
            .subscribe {
                if (it is VideoData) {
                    title.set(it.title)
                    query = it.query
                    reqSearch()
                }
            }
    }

    private fun reqSearch() {

        fun getQueryMap() =
            mapOf(
                "q" to (query ?: ResourceProvider.getString(R.string.app_name)),
                "maxResults" to 50,
                "order" to "viewCount",
                "type" to "video",
                "videoSyndicated" to "any",
                "key" to ResourceProvider.getStringArray(R.array.keys).first(),
                "part" to "snippet",
                "fields" to "items(id,snippet(title,thumbnails(medium)))"
            )

        fun onSuccess(_items: List<VideoItem>) {
            Timber.d("onSuccess")
            items.value = _items
        }

        fun onError(it: Throwable) {
            Timber.e(it)
            Timber.d((it as? HttpException)?.response()?.errorBody()?.string())
        }

        compositeDisposable += searchRequest.api()
            .search(getQueryMap())
            .map {
                searchRequest.toVideoItems(it, 5)
            }
            .doOnSubscribe { isLoading.set(true) }
            .doOnSuccess { isLoading.set(false) }
            .doOnError { isLoading.set(false) }
            .subscribeOn(SchedulerProvider.io())
            .observeOn(SchedulerProvider.ui())
            .subscribe(::onSuccess, ::onError)
    }

    data class VideoData(
        val title: String,
        val query: String
    )
}
