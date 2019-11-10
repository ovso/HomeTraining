package io.github.ovso.hometraining.data.api

import com.google.gson.JsonElement
import io.reactivex.Flowable
import okhttp3.Headers
import retrofit2.Response

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
        "key" to stringFromJNI(),
        "part" to "snippet"
    )
    return api.search(qMap)
  }

  suspend fun searchCoroutine(q: String): Response<JsonElement> {
    val qMap = hashMapOf(
        "q" to q,
        "maxResults" to 50,
        "order" to "viewCount",
        "type" to "video",
        "videoSyndicated" to "any",
        "key" to stringFromJNI(),
        "part" to "snippet"
    )

    return api.searchCoroutine(qMap)
  }

  private external fun stringFromJNI(): String

  companion object {
    init {
      System.loadLibrary("kotlin-jni")
    }
  }

}