package io.github.ovso.hometraining.view.binding

import android.view.View
import androidx.databinding.BindingAdapter
import java.util.concurrent.atomic.AtomicBoolean

@BindingAdapter("onThrottleClick")
fun View.setOnThrottleClickListener(
  clickListener: View.OnClickListener?
) {
  clickListener?.also {
    setOnClickListener(OnThrottleClickListener(it))
  } ?: setOnClickListener(null)
}

class OnThrottleClickListener(
  private val clickListener: View.OnClickListener,
  private val delayMillis: Long = 1000L
) : View.OnClickListener {
  private var canClick = AtomicBoolean(true)

  override fun onClick(v: View?) {
    if (canClick.getAndSet(false)) {
      v?.run {
        postDelayed({
          canClick.set(true)
        }, delayMillis)
        clickListener.onClick(v)
      }
    }
  }
}