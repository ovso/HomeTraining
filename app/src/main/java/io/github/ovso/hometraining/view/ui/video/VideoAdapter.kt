package io.github.ovso.hometraining.view.ui.video

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.hometraining.view.base.AdsViewHolder
import io.github.ovso.hometraining.view.ui.video.model.VideoItem

private const val VIEW_TYPE_ITEM = 1
private const val VIEW_TYPE_ADS = 2

class VideoAdapter : ListAdapter<VideoItem, RecyclerView.ViewHolder>(DiffUtil()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType == VIEW_TYPE_ITEM) {
            true -> VideoViewHolder.from(parent)
            false -> AdsViewHolder.from(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).videoId.isEmpty()) VIEW_TYPE_ADS else VIEW_TYPE_ITEM
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if (holder is VideoViewHolder) {
            holder.bind(getItem(position))
        } else if (holder is AdsViewHolder) {
            holder.bind(getItem(position))
        }
    }
}

private class DiffUtil : DiffUtil.ItemCallback<VideoItem>() {
    override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }

}

/*

            "kind": "youtube#searchResult",
            "etag": "\"j6xRRd8dTPVVptg711_CSPADRfg/K1OFMB8lmYWjSKsAYbp78ZojRPw\"",
            "id": {
                "kind": "youtube#video",
                "videoId": "5ksBYHaSBrE"
            },
            "snippet": {
                "publishedAt": "2018-08-30T08:00:01.000Z",
                "channelId": "UCCxThWKnqpKyvuLV0pjAIBg",
                "query": "[다이어트 기획 1편] 진짜 4주만에⁉️ 홈트레이닝으로만 11자 복근 만들기 도전‍♀️",
                "description": "안녕하세요 ^^ 유리에요! 제가 4주동안에 걸쳐 11자 복근 만들기에 도전을 했어요! 오늘 영상에서는 2주까지의 변화를 보여드렸구요, 다음주에...",
                "thumbnails": {
                    "default": {
                        "url": "https://i.ytimg.com/vi/5ksBYHaSBrE/default.jpg",
                        "width": 120,
                        "height": 90
                    },
                    "medium": {
                        "url": "https://i.ytimg.com/vi/5ksBYHaSBrE/mqdefault.jpg",
                        "width": 320,
                        "height": 180
                    },
                    "high": {
                        "url": "https://i.ytimg.com/vi/5ksBYHaSBrE/hqdefault.jpg",
                        "width": 480,
                        "height": 360
                    }
                },
                "channelTitle": "onme 온미",
                "liveBroadcastContent": "none"
            }


*/
