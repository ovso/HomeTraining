package io.github.ovso.hometraining.view.ui.player

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.utils.RxBusBehavior.VideoId
import io.github.ovso.hometraining.view.base.DisposableViewModel
import timber.log.Timber

class PlayerViewModel : DisposableViewModel() {
  val videoId = ObservableField<String>()
  val videoIdLive = MutableLiveData<String>()

  init {
    addDisposable(
        RxBusBehavior.toObservable().subscribe {
          if (it is VideoId) {
            Timber.d("videoId = ${it.id}")
            videoIdLive.postValue(it.id)
          }
        }
    )
  }
}