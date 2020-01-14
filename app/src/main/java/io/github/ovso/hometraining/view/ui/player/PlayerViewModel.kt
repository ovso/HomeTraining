package io.github.ovso.hometraining.view.ui.player

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.view.base.DisposableViewModel
import io.github.ovso.hometraining.view.ui.video.model.VideoItem

private const val BASE_URL_YOUTUBE = "https://www.youtube.com/watch?v="

class PlayerViewModel : DisposableViewModel() {
  val videoUrlOb = ObservableField<String>()
  val canGoBackOb = ObservableBoolean(false)
  val canGoForwardOb = ObservableBoolean(false)

  init {
    addDisposable(
        RxBusBehavior.toObservable().subscribe {
          if (it is VideoItem) {
            videoUrlOb.set("$BASE_URL_YOUTUBE${it.videoId}")
          }
        }
    )
  }
}
