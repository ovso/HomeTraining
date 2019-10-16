package io.github.ovso.hometraining.view.base

import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.Tab

abstract class SimpleOnTabSelectedListener : OnTabSelectedListener {
  override fun onTabReselected(tab: Tab?) {
  }

  override fun onTabUnselected(tab: Tab?) {
  }

  override fun onTabSelected(tab: Tab?) {
  }
}