package io.github.ovso.hometraining.view.ui.player

import androidx.databinding.ObservableField
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.utils.RxBusBehavior.VideoId
import io.github.ovso.hometraining.view.base.DisposableViewModel

private const val BASE_URL_YOUTUBE = "https://www.youtube.com/watch?v="

class PlayerViewModel : DisposableViewModel() {
  val videoUrlOb = ObservableField<String>()

  init {
    addDisposable(
        RxBusBehavior.toObservable().subscribe {
          if (it is VideoId) {
            videoUrlOb.set("$BASE_URL_YOUTUBE${it.id}")
          }
        }
    )
  }
}