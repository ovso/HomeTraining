package io.github.ovso.hometraining.view.gender

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable

class GenderViewModel(private val app: Application) : AndroidViewModel(app) {
    private val compositeDisposable = CompositeDisposable()
    val tabsInitLiveData = MutableLiveData<GenderAdapter.GenderAdapterItem>()

    init {
        getAdapterItem()
    }

    private fun getAdapterItem() {

//        Single.fromCallable {
//            app.resources.getStringArray(R.array.tabs_male)
//        }.map {
//        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
