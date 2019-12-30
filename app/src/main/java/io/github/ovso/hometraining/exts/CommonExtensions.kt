package io.github.ovso.hometraining.exts

import android.app.Activity
import android.content.Context
import org.jetbrains.anko.internals.AnkoInternals

inline fun <reified T: Activity> Context.startActivity(vararg params: Pair<String, Any?>) =
    AnkoInternals.internalStartActivity(this, T::class.java, params)
