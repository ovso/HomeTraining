package io.github.ovso.hometraining.view.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import io.github.ovso.hometraining.exts.startActivity
import io.github.ovso.hometraining.view.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            startActivity<MainActivity>()
            finish()
        }, 1000)
    }
}
