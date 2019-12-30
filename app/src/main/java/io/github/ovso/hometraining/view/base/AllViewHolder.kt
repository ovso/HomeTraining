package io.github.ovso.hometraining.view.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.exts.startActivity
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.view.ui.video.VideoActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_all.card_all_item_container
import kotlinx.android.synthetic.main.item_all.tv_all_item_title

class AllViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView!!),
    LayoutContainer {

  fun bind(data: TitleAndQuery) {
    tv_all_item_title.text = data.title
    card_all_item_container.setOnClickListener {
      RxBusBehavior.send(data)
      it.context.startActivity<VideoActivity>()
    }
  }

  companion object {
    fun create(parent: ViewGroup): AllViewHolder {
      return AllViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.item_all, parent, false)
      )
    }

    fun toBindData(
      title: String,
      query: String
    ) = TitleAndQuery(title, query)
  }

  data class TitleAndQuery(
    val title: String,
    val query: String
  )
}
