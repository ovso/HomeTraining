package io.github.ovso.hometraining.view.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonArray
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ItemVideoBinding
import io.github.ovso.hometraining.view.video.VideoAdapter.MyViewHolder

class VideoAdapter : RecyclerView.Adapter<MyViewHolder>() {
    var items: JsonArray? = null
    override fun getItemCount() = items?.size() ?: 0
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder.create(parent)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.bind(getItem(holder.itemView, position))
    }

    private fun getItem(
        view: View,
        position: Int
    ): VideoItemViewModel {
        return ViewModelProviders.of(view.context as FragmentActivity)[VideoItemViewModel::class.java].apply {
            val json = items!![position].asJsonObject
            videoId = json["id"].asJsonObject["videoId"].asString
            imgUrl = json["snippet"].asJsonObject["thumbnails"]
                .asJsonObject["high"].asJsonObject["url"].asString
        }
    }


    class MyViewHolder(
        var binding: ItemVideoBinding?
    ) : RecyclerView.ViewHolder(binding!!.root) {

        fun bind(viewModel: VideoItemViewModel) {
            binding?.apply {
                this.viewModel = viewModel
            }
        }


        companion object {
            fun create(parent: ViewGroup): MyViewHolder {
                return MyViewHolder(
                    DataBindingUtil.bind(
                        LayoutInflater.from(parent.context).inflate(
                            R.layout.item_video,
                            parent,
                            false
                        )
                    )
                )
            }
        }

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