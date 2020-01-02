package io.github.ovso.hometraining.exts

import android.app.Activity
import android.content.Context
import io.reactivex.rxjava3.annotations.Nullable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import org.jetbrains.anko.internals.AnkoInternals

inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) =
  AnkoInternals.internalStartActivity(this, T::class.java, params)

operator fun CompositeDisposable.plusAssign(@Nullable d: Disposable?) {
    this.add(d)
}