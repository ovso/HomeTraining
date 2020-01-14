package io.github.ovso.hometraining.data.api

import io.github.ovso.hometraining.data.model.Video
import io.github.ovso.hometraining.view.ui.video.model.VideoItem

class SearchRequest : BaseRequest<SearchService>(
    BASE_URL,
    SearchService::class.java
) {

    fun toVideoItems(video: Video, adsStep: Int = 5): List<VideoItem> {
        val originItems = video.items
        val newItems = mutableListOf<VideoItem>()
        val count = originItems.count()
        for (i in 0 until count step adsStep) {
            println(i)
            val toIndex = if (i + adsStep > count) count else i + adsStep
            val subList = originItems.subList(i, toIndex).map {
                VideoItem(
                    videoId = it.id.videoId,
                    imgUrl = it.snippet.thumbnails.medium.url
                )
            }.toList()
            newItems.add(VideoItem())
            newItems.addAll(subList)
        }
        return ArrayList(newItems)
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
