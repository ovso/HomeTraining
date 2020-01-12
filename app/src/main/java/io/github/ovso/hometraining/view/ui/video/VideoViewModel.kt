package io.github.ovso.hometraining.view.ui.video

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.data.api.SearchRequest
import io.github.ovso.hometraining.data.model.Item
import io.github.ovso.hometraining.data.model.Video
import io.github.ovso.hometraining.exts.plusAssign
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
    val itemsLive: LiveData<List<Item>> = MutableLiveData<List<Item>>()

    val titleOb = ObservableField<String>()
    private var query: String? = null

    init {
        compositeDisposable += RxBusBehavior.toObservable()
            .subscribe {
                if (it is VideoData) {
                    titleOb.set(it.title)
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
                "key" to ResourceProvider.getStringArray(R.array.keys)[KeyPreferences.index],
                "part" to "snippet",
                "fields" to "items(id,snippet(title,thumbnails(medium)))"
            )

        fun onSuccess(it: Video) {
            Timber.d("onSuccess")
            (itemsLive as? MutableLiveData<List<Item>>)?.value = it.items
        }

        fun onError(it: Throwable) {
            Timber.e(it)
            Timber.d((it as? HttpException)?.response()?.errorBody()?.string())
        }

        compositeDisposable += searchRequest.api()
            .search(getQueryMap())
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
