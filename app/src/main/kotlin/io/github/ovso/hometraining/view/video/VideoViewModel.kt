package io.github.ovso.hometraining.view.video

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.ovso.hometraining.data.api.SearchRequest
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*

class VideoViewModel : ViewModel() {
  val _snackbar: MutableLiveData<String> = MutableLiveData()
  var items = ObservableArrayList<Any>()
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
      delay(10000)
      println("launch")
    }

  }

  override fun onCleared() {
    compositeDisposable.clear()
    viewModelJob.cancel()
  }

  fun onMainViewClicked() {
    // launch a coroutine in viewModelScope
    uiScope.launch {
      // suspend this coroutine for one second
      delay(1_000)
      // resume in the main dispatcher
      // _snackbar.value can be called directly from main thread
      _snackbar.value = "Hello, from coroutines!"
    }
  }
}
