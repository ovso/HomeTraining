package io.github.ovso.hometraining.data.api

import com.google.gson.JsonElement
import io.reactivex.Single
import okhttp3.Headers
import timber.log.Timber

class SearchRequest : BaseRequest<SearchService>() {

  private var apiKeyIndex = 1
  private val apiKeys by lazy {
    apiKeys().split("//".toRegex())
  }

  private fun nextApiKey(): String {
    Timber.d("apiKeyIndex = $apiKeyIndex")
    if ((apiKeyIndex == apiKeys.count())) {
      apiKeyIndex = 0
    }
    val key = apiKeys[apiKeyIndex]
    ++apiKeyIndex
    return key
  }

  override val apiClass: Class<SearchService> = SearchService::class.java

  override val baseUrl: String = "https://www.googleapis.com/"

  override fun createHeaders() = Headers.Builder().build()

  fun search(q: String): Single<JsonElement> {
    val qMap = hashMapOf(
        "q" to q,
        "maxResults" to 50,
        "order" to "viewCount",
        "type" to "video",
        "videoSyndicated" to "any",
        "key" to nextApiKey(),
        "part" to "snippet",
        "fields" to "items(id,snippet(title,thumbnails(medium)))"
    )
    return api.search(qMap)
  }

  private external fun apiKeys(): String

  companion object {
    init {
      System.loadLibrary("kotlin-jni")
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