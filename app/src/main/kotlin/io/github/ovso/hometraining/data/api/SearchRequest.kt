package io.github.ovso.hometraining.data.api

import com.google.gson.JsonElement
import io.reactivex.Flowable
import okhttp3.Headers

class SearchRequest : BaseRequest<SearchService>() {

  override val apiClass: Class<SearchService> = SearchService::class.java

  override val baseUrl: String = "https://www.googleapis.com/"

  override fun createHeaders() = Headers.Builder().build()

  fun search(q: String): Flowable<JsonElement> {
    val qMap = hashMapOf(
        "q" to q,
        "maxResults" to 50,
        "order" to "viewCount",
        "type" to "video",
        "videoSyndicated" to "any",
        "key" to "AIzaSyA4pdIQO-74kZv7MLpPZs13oEYq2w5ki4E",
        "part" to "snippet"
    )
    return api.search(qMap)
  }
}
//https://www.googleapis.com/youtube/v3/search?key=AIzaSyA4pdIQO-74kZv7MLpPZs13oEYq2w5ki4E&part=snippet&maxResults=50&type=video&order=viewCount&videoSyndicated=any&q=여자%20복근