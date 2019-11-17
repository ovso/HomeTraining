package io.github.ovso.hometraining.view.video

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.google.gson.JsonObject

class VideoItemViewModel(var app: Application) : AndroidViewModel(app) {

    var videoId: String? = null
    var imgUrl: String? = null

    fun setData(json: JsonObject) {
        videoId = json["id"]
            ?.asJsonObject?.get("videoId")
            ?.asString
        imgUrl =
            json["snippet"]?.asJsonObject?.get("thumbnails")
                ?.asJsonObject?.get("high")
                ?.asJsonObject?.get("url")?.asString
    }

    fun onClick() {
        Toast.makeText(app, "엑소플레이어 시작", Toast.LENGTH_SHORT).show()
    }
}