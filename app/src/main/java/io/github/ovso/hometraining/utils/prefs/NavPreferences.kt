package io.github.ovso.hometraining.utils.prefs

import github.agustarc.koap.PreferenceHolder
import github.agustarc.koap.delegator.ReadWriteInt

object NavPreferences : PreferenceHolder(name = "prefs_nav", default = true) {
  var position: Int by ReadWriteInt(key = "key_nav_position", default = 0)
}
