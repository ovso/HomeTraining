package io.github.ovso.hometraining.utils

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

object SchedulerProvider {
    fun io(): Scheduler = Schedulers.io()
    fun ui(): Scheduler = AndroidSchedulers.mainThread()
    fun computation() = Schedulers.computation()
    // fun new() = Schedulers.newThread()
}