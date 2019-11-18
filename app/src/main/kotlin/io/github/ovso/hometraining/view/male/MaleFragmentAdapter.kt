package io.github.ovso.hometraining.view.male

import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.view.main.BottomNavPosition
import io.github.ovso.hometraining.view.main.BottomNavPosition.FEMALE
import io.github.ovso.hometraining.view.main.BottomNavPosition.MALE
import io.github.ovso.hometraining.view.main.BottomNavPosition.POPULAR
import io.github.ovso.hometraining.view.video.VideoFragment

class MaleFragmentAdapter(fm: FragmentManager) :
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

  fun handleData(navPosition: BottomNavPosition) {
    when (navPosition) {
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