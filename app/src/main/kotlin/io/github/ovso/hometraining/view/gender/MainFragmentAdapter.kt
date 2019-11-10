package io.github.ovso.hometraining.view.gender

import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.view.main.BottomMenu
import io.github.ovso.hometraining.view.main.BottomMenu.FEMALE
import io.github.ovso.hometraining.view.main.BottomMenu.MALE
import io.github.ovso.hometraining.view.main.BottomMenu.POPULAR
import io.github.ovso.hometraining.view.video.VideoFragment
import java.lang.UnsupportedOperationException

class MainFragmentAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
  private val titles = mutableListOf<String>()
  private val queries = mutableListOf<String>()
  override fun getItem(position: Int) = VideoFragment.newInstance().apply {
    arguments = bundleOf("query" to queries[position])
  }

  override fun getCount() = titles.size

  override fun getPageTitle(position: Int): CharSequence? {
    return titles[position]
  }

  override fun destroyItem(
    container: ViewGroup,
    position: Int,
    `object`: Any
  ) {
//    super.destroyItem(container, position, `object`)
  }

  fun handleData(menu: BottomMenu) {
    when (menu) {
      MALE -> handleMale()
      FEMALE -> handleFemale()
      POPULAR -> handlePopular()
    }
    notifyDataSetChanged()
  }

  private fun handlePopular() {
    titles.addAll(ResourceProvider.getStringArray(R.array.tabs_title_female))
    queries.addAll(ResourceProvider.getStringArray(R.array.queries_female))
  }

  private fun handleFemale() {
    titles.addAll(ResourceProvider.getStringArray(R.array.tabs_title_female))
    queries.addAll(ResourceProvider.getStringArray(R.array.queries_female))
  }

  private fun handleMale() {
    titles.addAll(ResourceProvider.getStringArray(R.array.tabs_title_male))
    queries.addAll(ResourceProvider.getStringArray(R.array.queries_male))
  }

}