package io.github.ovso.hometraining.view.ui.player2

import androidx.lifecycle.MutableLiveData
import io.github.ovso.hometraining.exts.plusAssign
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.view.base.DisposableViewModel

class Player2ViewModel : DisposableViewModel() {
    val videoIdLive = MutableLiveData<String>()

    var second: Float = 0F

    init {
        compositeDisposable += RxBusBehavior.toObservable().subscribe {
            if (it is VideoId) {
                videoIdLive.postValue(it.id)
            }
        }
    }

    data class VideoId(val id: String)
}
