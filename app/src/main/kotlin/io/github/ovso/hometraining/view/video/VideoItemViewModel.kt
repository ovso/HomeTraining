package io.github.ovso.hometraining.view.video

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.google.gson.JsonObject
import io.github.ovso.hometraining.view.player.PlayerActivity
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import timber.log.Timber

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

        Timber.d(json["snippet"]?.toString())
    }

    fun onClick(v: View) {
        val ctx = v.context
        v.context.startActivity(ctx.intentFor<PlayerActivity>().clearTop().singleTop())
    }
}