package io.github.ovso.hometraining.view.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.view.gender.MainFragment
import io.github.ovso.hometraining.view.main.BottomMenu.FEMALE
import io.github.ovso.hometraining.view.main.BottomMenu.MALE
import io.github.ovso.hometraining.view.main.BottomMenu.POPULAR
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.activity_main.nav_view
import kotlinx.android.synthetic.main.app_bar_main.toolbar
import kotlinx.android.synthetic.main.content_main.bnv_main
import kotlinx.android.synthetic.main.content_main.viewpager_main

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setupActionBar()
    setupDrawer()
    setupBottomNavView()
    setupViewPager()
  }

  private fun setupViewPager() {
    with(viewpager_main) {
      adapter = MainAdapter(supportFragmentManager)
          .apply {
            items = mutableListOf(
                MainFragment.newInstance(MALE),
                MainFragment.newInstance(FEMALE),
                MainFragment.newInstance(POPULAR)
            )
          }
    }
  }

  private fun setupBottomNavView() {
    bnv_main.setOnNavigationItemSelectedListener {
      when (it.itemId) {
        R.id.bottom_nv_male -> setCurItemForViewPager(MALE.position)
        R.id.bottom_nv_female -> setCurItemForViewPager(FEMALE.position)
        R.id.bottom_nv_popular -> setCurItemForViewPager(POPULAR.position)
      }
      true
    }
  }

  private fun setCurItemForViewPager(position: Int) {
    viewpager_main.setCurrentItem(position, false)
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
    nav_view.setNavigationItemSelectedListener(this)
  }

  private fun setupActionBar() {
    setSupportActionBar(toolbar)
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
