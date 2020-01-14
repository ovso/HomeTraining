package io.github.ovso.hometraining.view.ui.player2

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.view.base.DisposableViewModel
import io.github.ovso.hometraining.view.ui.video.model.VideoItem
import timber.log.Timber

class Player2ViewModel : DisposableViewModel() {
    val videoIdOb = ObservableField<String>("S0Q4gqBUs7c")
    val videoIdLive = MutableLiveData<String>()

    var second: Float = 0F

    init {
        Timber.d("OJH init!!!")
        addDisposable(
            RxBusBehavior.toObservable().subscribe {
                if (it is VideoItem) {
                    videoIdOb.set(it.videoId)
                    videoIdLive.postValue(it.videoId)
                }
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("OJH onCleared!!!")
    }
}
