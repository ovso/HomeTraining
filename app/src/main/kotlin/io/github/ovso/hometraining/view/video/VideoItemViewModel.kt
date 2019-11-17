package io.github.ovso.hometraining.view.video

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.google.gson.JsonObject

class VideoItemViewModel(var app: Application) : AndroidViewModel(app) {

    var videoId: String? = null
    var imgUrl: String? = null

    fun setData(json: JsonObject) {
        videoId = json["id"].asJsonObject["videoId"].asString
        imgUrl =
            json["snippet"].asJsonObject["thumbnails"].asJsonObject["high"].asJsonObject["url"].asString
    }

    fun onClick() {
        Toast.makeText(app, "onClick", Toast.LENGTH_SHORT).show()
    }

//    videoId = json["id"].asJsonObject["videoId"].asString
//    imgUrl = json["snippet"].asJsonObject["thumbnails"]
//    .asJsonObject["high"].asJsonObject["url"].asString

}