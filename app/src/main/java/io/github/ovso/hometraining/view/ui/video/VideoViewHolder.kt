package io.github.ovso.hometraining.view.ui.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.data.model.Item
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.view.ui.player2.Player2Activity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_video.*
import org.jetbrains.anko.startActivity

class VideoViewHolder(
    override val containerView: View?
) : RecyclerView.ViewHolder(containerView!!), LayoutContainer {

    private lateinit var item: Item

    fun bind(_item: Item) {
        item = _item
        loadImg()
        setClick()
    }

    private fun loadImg() {
        Glide.with(itemView)
            .load(getImgUrl())
            .into(iv_video_item)
    }

    private fun setClick() {
        fl_video_item_img_container.setOnClickListener {
            RxBusBehavior.send(toVideoId())
            it.context.startActivity<Player2Activity>()
        }
    }

    private fun toVideoId() = RxBusBehavior.VideoId(item.id?.videoId!!)

    private fun getImgUrl(): String? {
        return item.snippet?.thumbnails?.medium?.url
    }

    companion object {
        fun create(parent: ViewGroup): VideoViewHolder {
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
