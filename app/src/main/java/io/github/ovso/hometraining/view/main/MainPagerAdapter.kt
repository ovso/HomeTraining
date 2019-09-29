package io.github.ovso.hometraining.view.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var items = mutableListOf<Fragment>()
    override fun getItem(position: Int) = items[position]

    override fun getCount() = items.size

}