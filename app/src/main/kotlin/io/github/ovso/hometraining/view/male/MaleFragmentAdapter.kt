package io.github.ovso.hometraining.view.male

import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.utils.ResourceProvider
import io.github.ovso.hometraining.view.video.VideoFragment

class MaleFragmentAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
  private val titles by lazy {
    ResourceProvider.getStringArray(R.array.tabs_title_male)
  }
  private val queries by lazy {
    ResourceProvider.getStringArray(R.array.queries_male)
  }

  override fun getItem(position: Int) =
    VideoFragment.newInstance().apply {
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

}