package io.github.ovso.hometraining.view.ui.video.model

import android.view.View
import io.github.ovso.hometraining.exts.startActivity
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.view.ui.player2.Player2Activity

data class VideoItem(
    val imgUrl: String = "",
    val videoId: String = ""
) {

    fun onClick(v: View) {
        RxBusBehavior.send(RxBusBehavior.send(this))
        v.context.startActivity<Player2Activity>()
    }
}
