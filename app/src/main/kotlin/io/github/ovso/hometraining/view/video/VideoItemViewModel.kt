package io.github.ovso.hometraining.view.video

import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject

class VideoItemViewModel(json: JsonObject) : ViewModel() {

    val videoId by lazy {
        json["id"].asJsonObject["videoId"].asString
    }
    val imgUrl by lazy {
        json["snippet"].asJsonObject["thumbnails"].asJsonObject["high"].asJsonObject["url"].asString
    }

}