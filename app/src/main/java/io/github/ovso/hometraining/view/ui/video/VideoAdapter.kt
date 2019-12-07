package io.github.ovso.hometraining.view.ui.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.utils.RxBusBehavior
import io.github.ovso.hometraining.utils.RxBusBehavior.VideoId
import io.github.ovso.hometraining.view.ui.player.PlayerActivity
import io.github.ovso.hometraining.view.ui.video.VideoAdapter.MyViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_video.fl_video_item_img_container
import kotlinx.android.synthetic.main.item_video.iv_video_item
import org.jetbrains.anko.startActivity

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
    holder.bind(items!![position].asJsonObject!!)
  }

  class MyViewHolder(
    override val containerView: View?
  ) : RecyclerView.ViewHolder(containerView!!), LayoutContainer {

    private lateinit var json: JsonObject

    fun bind(_json: JsonObject) {
      json = _json
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
        it.context.startActivity<PlayerActivity>()
      }
    }

    private fun toVideoId() = VideoId(json["id"].asJsonObject["videoId"].asString)

    private fun getImgUrl(): String? {
      return json["snippet"]?.asJsonObject?.get("thumbnails")
          ?.asJsonObject?.get("medium")
          ?.asJsonObject?.get("url")
          ?.asString
    }

    companion object {
      fun create(parent: ViewGroup): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_video,
                parent,
                false
            )

        return MyViewHolder(view)
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