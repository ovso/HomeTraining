package io.github.ovso.hometraining.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.hometraining.BR
import io.github.ovso.hometraining.BuildConfig

open class GenderViewHolder(
    @LayoutRes layoutRes: Int,
    parent: ViewGroup?
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent?.context).inflate(layoutRes, parent, false)
) {
  protected val binding: ViewDataBinding = DataBindingUtil.bind(itemView)!!

  open fun onBindViewHolder(item: Any?) {
    try {
      binding.run {
        setVariable(BR.item, item)
        executePendingBindings()
      }
    } catch (e: Exception) {
      if (BuildConfig.DEBUG) {
        e.printStackTrace()
      }
    }
  }
}
