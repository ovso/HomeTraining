package io.github.ovso.hometraining.utils

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object RxBus {
    private val bus = PublishSubject.create<Any>()

    fun send(o: Any) {
        bus.onNext(o)
    }

    fun toObservable(): Observable<Any> {
        return bus
    }

    fun hasObservable(): Boolean {
        return bus.hasObservers()
    }
}
