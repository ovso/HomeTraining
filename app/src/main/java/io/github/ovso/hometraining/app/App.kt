package io.github.ovso.hometraining.app

import android.app.Application
import io.github.ovso.hometraining.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {
  companion object {
    lateinit var instance: Application
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
    setupTimber()
  }

  private fun setupTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }
  }
}