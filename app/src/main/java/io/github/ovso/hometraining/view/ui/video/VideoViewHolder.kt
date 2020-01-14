package io.github.ovso.hometraining.view.ui.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.view.ui.player2.Player2Activity
import io.github.ovso.hometraining.view.ui.video.model.VideoItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_video.*
import org.jetbrains.anko.startActivity

class VideoViewHolder(
    override val containerView: View?
) : RecyclerView.ViewHolder(containerView!!), LayoutContainer {


    fun bind(_item: VideoItem) {
        Glide.with(itemView)
            .load(_item.imgUrl)
            .into(iv_video_item)

        fl_video_item_img_container.setOnClickListener {
            RxBusBehavior.send(RxBusBehavior.send(_item))
            it.context.startActivity<Player2Activity>()
        }
    }

    companion object {
        fun from(parent: ViewGroup): VideoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_video,
                    parent,
                    false
                )

            return VideoViewHolder(view)
        }
    }
}
