package io.github.ovso.hometraining.view.video

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel

class VideoItemViewModel(var app: Application) : AndroidViewModel(app) {

    var videoId: String? = null
    var imgUrl: String? = null

    fun onClick() {
        Toast.makeText(app, "onClick", Toast.LENGTH_SHORT).show()
    }

}