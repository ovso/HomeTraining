package io.github.ovso.hometraining.view.gender

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class GenderAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
  var items = mutableListOf<GenderAdapterItem>()
  override fun getItem(position: Int) = items[position].fragment

  override fun getCount() = items.size

  override fun getPageTitle(position: Int): CharSequence? {
    return items[position].title
  }

  class GenderAdapterItem(
    val fragment: Fragment,
    val title: String
  )

}