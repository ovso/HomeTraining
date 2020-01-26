package io.github.ovso.hometraining.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

interface Providers {
    fun io() = Schedulers.io()
    fun ui() = AndroidSchedulers.mainThread()
    fun computation() = Schedulers.computation()
    fun new() = Schedulers.newThread()
    fun test() = Schedulers.trampoline()
}

object SchedulerProvider : Providers

object TestSchedulerProvider : Providers {
    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }
}
