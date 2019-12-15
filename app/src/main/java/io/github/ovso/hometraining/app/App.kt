package io.github.ovso.hometraining.app

import android.app.Application
import github.agustarc.koap.Koap
import io.github.ovso.hometraining.BuildConfig
import io.github.ovso.hometraining.utils.prefs.KeyPreferences
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
    setupPrefs()
  }

  private fun setupPrefs() {
    Koap.bind(this, KeyPreferences)
  }

  private fun setupTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }
  }
}