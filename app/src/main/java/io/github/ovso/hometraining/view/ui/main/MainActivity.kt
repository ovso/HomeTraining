package io.github.ovso.hometraining.view.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.material.navigation.NavigationView
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ActivityMainBinding
import io.github.ovso.hometraining.exts.FragmentExtensions.attach
import io.github.ovso.hometraining.exts.FragmentExtensions.detach
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.utils.prefs.NavPreferences
import io.github.ovso.hometraining.view.base.DataBindingActivity2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : DataBindingActivity2<ActivityMainBinding>(
    layoutResId = R.layout.activity_main,
    viewModelCls = MainViewModel::class.java
), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar()
//    setupDrawer()
        initFragment()
        addEvent()
        setupAds()
    }

    private fun setupAds() {
        val adView = AdView(this)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        ad_container.addView(adView)
    }

    private fun addEvent() {
        bottom_nav_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_nv_male -> switchFragment(BottomNavPosition.MALE)
                R.id.bottom_nv_female -> switchFragment(BottomNavPosition.FEMALE)
                else -> switchFragment(BottomNavPosition.POPULAR)
            }
        }
    }

    private fun initFragment() {
        val navPosition = BottomNavPosition.values()
            .first {
                it.position == NavPreferences.position
            }
        bottom_nav_main.selectedItemId = bottom_nav_main.menu[navPosition.position].itemId
        switchFragment(navPosition)
    }

    /**
     * Immediately execute transactions with FragmentManager#executePendingTransactions.
     */
    private fun switchFragment(navPosition: BottomNavPosition): Boolean {
        NavPreferences.position = navPosition.position
        return findFragment(navPosition).let {
            if (it.isAdded) return false
            supportFragmentManager.detach() // Extension function
            supportFragmentManager.attach(it, navPosition.getTag()) // Extension function
            supportFragmentManager.executePendingTransactions()
        }
    }

    private fun findFragment(position: BottomNavPosition): Fragment {
        return supportFragmentManager.findFragmentByTag(position.getTag())
            ?: position.createFragment()
    }

    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
//    nav_view.setNavigationItemSelectedListener(this)
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = ResourceProvider.getString(R.string.app_name)
        // query..
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {
            }
            R.id.nav_slideshow -> {
            }
            R.id.nav_tools -> {
            }
            R.id.nav_share -> {
            }
            R.id.nav_send -> {
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
