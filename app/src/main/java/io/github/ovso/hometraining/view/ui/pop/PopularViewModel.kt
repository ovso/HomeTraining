package io.github.ovso.hometraining.view.ui.pop

import androidx.databinding.ObservableField
import com.google.gson.JsonArray
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.data.api.SearchRequest
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.utils.SchedulerProvider
import io.github.ovso.hometraining.view.base.DisposableViewModel
import io.reactivex.rxkotlin.subscribeBy
import retrofit2.HttpException
import timber.log.Timber

class PopularViewModel : DisposableViewModel() {
  val items = ObservableField<JsonArray>()
  private val searchRequest by lazy { SearchRequest() }

  init {
    reqSearch()
  }

  private fun reqSearch() {
    val disposable =
      searchRequest
          .search(ResourceProvider.getString(R.string.popular_query))
          .subscribeOn(SchedulerProvider.io())
          .observeOn(SchedulerProvider.ui())
          .doOnError {
            Timber.e(it)
            Timber.d((it as? HttpException)?.response()?.errorBody()?.string())
          }
          .subscribeBy {
            items.set(it.asJsonObject["items"].asJsonArray)
          }
    addDisposable(disposable)
  }

}
