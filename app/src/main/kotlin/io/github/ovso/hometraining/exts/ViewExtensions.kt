package io.github.ovso.hometraining.exts

import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE

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
}