package io.github.ovso.hometraining.view.ui.video.model

import android.view.View
import com.google.android.gms.ads.AdRequest
import io.github.ovso.hometraining.exts.startActivity
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.view.ui.player2.Player2Activity
import io.github.ovso.hometraining.view.ui.player2.Player2ViewModel

data class VideoItem(
    val imgUrl: String = "",
    val videoId: String = ""
) {

    fun onClick(v: View) {
        RxBusBehavior.send(Player2ViewModel.VideoId(videoId))
        v.context.startActivity<Player2Activity>()
    }

    val adReq: AdRequest by lazy { AdRequest.Builder().build() }
}
