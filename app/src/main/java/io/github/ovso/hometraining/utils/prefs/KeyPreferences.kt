package io.github.ovso.hometraining.utils.prefs

import github.agustarc.koap.PreferenceHolder
import github.agustarc.koap.delegator.ReadWriteInt

object KeyPreferences : PreferenceHolder(name = "prefs_key", default = true) {
    var index: Int by ReadWriteInt(key = "key_api_key_index", default = -1)
}
