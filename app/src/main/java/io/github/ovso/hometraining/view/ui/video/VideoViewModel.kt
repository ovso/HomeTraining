package io.github.ovso.hometraining.view.ui.video

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.uber.autodispose.autoDispose
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.data.api.SearchRequest
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
  val errorDialogLive = MutableLiveData<Throwable>()

  val titleOb = ObservableField<String>()
  private var query: String? = null

  init {
    RxBusBehavior.toObservable()
        .autoDispose(this)
        .subscribe {
          if (it is TitleAndQuery) {
            titleOb.set(it.title)
            query = it.query
            Timber.d("title = ${it.title}, query = ${it.query}")
            reqSearch()
          }
        }
  }

  private fun reqSearch() {
    Timber.d("reqSearch query = $query")
    searchRequest
        .search(
            query ?: ResourceProvider.getString(R.string.main_nav_title_male)
        )
        .subscribeOn(SchedulerProvider.io())
        .observeOn(SchedulerProvider.ui())
        .autoDispose(this)
        .subscribe(onSuccess, onError, onComplete)
  }

  private val onError: (Throwable) -> Unit = {
    Timber.d((it as? HttpException)?.response()?.errorBody()?.string())
  }

  private val onSuccess: (JsonElement) -> Unit = {
    items.set(it.asJsonObject["items"].asJsonArray)
  }

  private val onComplete: () -> Unit = {
    Timber.d("onComplete()")
  }
}