package io.github.ovso.hometraining.app

import android.app.Application
import github.agustarc.koap.Koap
import io.github.ovso.hometraining.BuildConfig
import io.github.ovso.hometraining.R
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
    if (KeyPreferences.index == resources.getStringArray(R.array.keys).lastIndex) {
      KeyPreferences.index = -1
    }
    ++KeyPreferences.index
    Timber.d("KeyPreferences.index = ${KeyPreferences.index}")
  }

  private fun setupTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }
  }
}