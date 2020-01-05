package io.github.ovso.hometraining.view.ui.male

import android.view.View
import io.github.ovso.hometraining.exts.startActivity
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.view.ui.video.VideoActivity
import io.github.ovso.hometraining.view.ui.video.VideoViewModel.VideoData

data class MaleItem(
    val title: String,
    val query: String
) {

  fun onClick(v: View) {
    RxBusBehavior.send(VideoData(title, query))
    v.context.startActivity<VideoActivity>()
  }
}
