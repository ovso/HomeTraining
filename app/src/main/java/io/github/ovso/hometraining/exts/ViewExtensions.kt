package io.github.ovso.hometraining.exts

import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import timber.log.Timber

object ViewExtensions {

  fun View.gone() {
    visibility = GONE
  }

  fun View.invisible() {
    visibility = INVISIBLE
  }

  fun View.visible() {
    visibility = VISIBLE
  }

  /**
   * Created by Yasuhiro Suzuki on 2017/07/15.
   *
   * This can be used in Support Library 27 or lower.
   * See http://stackoverflow.com/questions/40176244/how-to-disable-bottomnavigationview-shift-mode
   */

  fun BottomNavigationView.disableShiftMode() {
    val menuView = getChildAt(0) as BottomNavigationMenuView
    try {
      menuView.javaClass.getDeclaredField("mShiftingMode").also { shiftMode ->
        shiftMode.isAccessible = true
        shiftMode.setBoolean(menuView, false)
        shiftMode.isAccessible = false
      }
      for (i in 0 until menuView.childCount) {
        (menuView.getChildAt(i) as BottomNavigationItemView).also { item ->
          item.setShifting(false)
          item.setChecked(item.itemData.isChecked)
        }
      }
    } catch (t: Throwable) {
      Timber.e("BottomNavigationHelper", "Unable to get shift mode field", t)
    } catch (e: IllegalAccessException) {
      Timber.e("BottomNavigationHelper", "Unable to change value of shift mode", e)
    }
  }

  /**
   * Make selected position active
   */
  fun BottomNavigationView.active(position: Int) {
    menu.getItem(position).isChecked = true
  }
}