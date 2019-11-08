package io.github.ovso.hometraining.app

import android.app.Application
import android.util.Log
import io.github.ovso.hometraining.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {
  companion object {
    lateinit var instance:Application
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
    setupTimber()
  }

  private fun setupTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    } else {
      Timber.plant(CrashReportingTree())
    }
  }

  /** A tree which logs important information for crash reporting.  */
  private class CrashReportingTree : Timber.Tree() {
    override fun log(
      priority: Int,
      tag: String?,
      message: String,
      t: Throwable?
    ) {
      if (priority == Log.VERBOSE || priority == Log.DEBUG) {
        return
      }

      FakeCrashLibrary.log(priority, tag, message)

      if (t != null) {
        if (priority == Log.ERROR) {
          FakeCrashLibrary.logError(t)
        } else if (priority == Log.WARN) {
          FakeCrashLibrary.logWarning(t)
        }
      }
    }
  }
}