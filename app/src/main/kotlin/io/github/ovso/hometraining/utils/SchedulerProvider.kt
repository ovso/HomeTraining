package io.github.ovso.hometraining.utils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SchedulerProvider {
    fun io() = Schedulers.io()
    fun ui() = AndroidSchedulers.mainThread()
    fun computation() = Schedulers.computation()
    // fun new() = Schedulers.newThread()
}