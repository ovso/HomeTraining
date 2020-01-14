package io.github.ovso.hometraining.view.ui.video.model

data class VideoItem(
    val imgUrl: String = "",
    val videoId: String = ""
) {

}

fun to(): VideoItem {
    return VideoItem()
}
