package io.github.ovso.hometraining.view.ui.player

import androidx.lifecycle.MutableLiveData
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.utils.RxBusBehavior.VideoId
import io.github.ovso.hometraining.view.base.DisposableViewModel

class PlayerViewModel : DisposableViewModel() {
  val videoIdLive = MutableLiveData<String>()

  init {
    addDisposable(
        RxBusBehavior.toObservable().subscribe {
          if (it is VideoId) {
            videoIdLive.postValue(it.id)
          }
        }
    )
  }
}