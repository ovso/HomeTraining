package io.github.ovso.hometraining.view.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.view.ui.video.VideoActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_all.card_all_item_container
import kotlinx.android.synthetic.main.item_all.tv_all_item_title
import org.jetbrains.anko.startActivity

class AllViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView!!),
    LayoutContainer {

  fun bind(title: String) {
    tv_all_item_title.text = title
    card_all_item_container.setOnClickListener {
      val ctx = it.context
      ctx.startActivity<VideoActivity>()
    }
  }

  companion object {
    fun create(parent: ViewGroup): AllViewHolder {
      return AllViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.item_all, parent, false)
      )
    }
  }
}
