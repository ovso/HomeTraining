package io.github.ovso.hometraining.utils

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

object RxBusBehavior {

  private val bus = BehaviorSubject.create<Any>()

  fun send(o: Any) {
    bus.onNext(o)
  }

  fun toObservable(): Observable<Any> {
    return bus
  }

  data class VideoTitleAndQuery(
    val title: String,
    val query: String
  )

  class VideoId(val id: String)

}