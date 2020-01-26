package io.github.ovso.hometraining.view.binding

import android.view.View
import androidx.databinding.BindingAdapter
import io.github.ovso.hometraining.exts.gone
import io.github.ovso.hometraining.exts.invisible
import io.github.ovso.hometraining.exts.visible
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

@BindingAdapter("visible_space")
fun setSpaceVisible(
    v: View,
    enable: Boolean?
) {
    enable?.let {
        if (it) {
            v.visible()
        } else {
            v.invisible()
        }
    }
}

@BindingAdapter("visible")
fun setVisible(
    v: View,
    enable: Boolean?
) {
    enable?.let {
        if (it) {
            v.visible()
        } else {
            v.gone()
        }
    }
}
