package io.github.ovso.hometraining.exts

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import io.github.ovso.hometraining.R

object FragmentExtensions {

  fun FragmentManager.detach() {
    findFragmentById(R.id.fl_fragment_container)?.also {
      beginTransaction().detach(it)
          .commit()
    }
  }

  fun FragmentManager.attach(
      fragment: Fragment,
      tag: String
  ) {
    if (fragment.isDetached) {
      beginTransaction().attach(fragment)
          .commit()
    } else {
      beginTransaction().add(R.id.fl_fragment_container, fragment, tag)
          .commit()
    }
    // Set a transition animation for this transaction.
    beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        .commit()
  }
}
