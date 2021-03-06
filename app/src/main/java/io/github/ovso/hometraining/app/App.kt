package io.github.ovso.hometraining.app

import android.app.Application
import com.google.android.gms.ads.MobileAds
import github.agustarc.koap.Koap
import io.github.ovso.hometraining.BuildConfig
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.di.appModule
import io.github.ovso.hometraining.utils.prefs.NavPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
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
        setupDi()
        setupAds()
    }

    private fun setupAds() {
        MobileAds.initialize(this, getString(R.string.ads_app_id))
    }

    private fun setupDi() {
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }

    private fun setupPrefs() {
        Koap.bind(this, NavPreferences)
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}
