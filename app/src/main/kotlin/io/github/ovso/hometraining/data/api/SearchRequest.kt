package io.github.ovso.hometraining.data.api

import com.google.gson.JsonElement
import io.reactivex.Flowable
import okhttp3.Headers
import retrofit2.Response
import timber.log.Timber

class SearchRequest : BaseRequest<SearchService>() {

  private var apiKeyIndex = 3
  private val apiKeys by lazy {
    apiKeys().split("//".toRegex())
  }

  private fun nextApiKey(): String {
    Timber.d("apiKeyIndex = $apiKeyIndex")
    if ((apiKeyIndex == apiKeys.count())) {
      apiKeyIndex = 0
    }
    val key = apiKeys[apiKeyIndex]
    ++ apiKeyIndex
    return key
  }

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
        "key" to nextApiKey(),
        "part" to "snippet"
    )
    return api.search(qMap)
  }

  suspend fun searchCoroutine(q: String): Response<JsonElement> {
    val key = nextApiKey()
    Timber.d("key = $key")
    val qMap = hashMapOf(
        "q" to q,
        "maxResults" to 50,
        "order" to "viewCount",
        "type" to "video",
        "videoSyndicated" to "any",
        "key" to key,
        "part" to "snippet"
    )

    return api.searchCoroutine(qMap)
  }

  private external fun apiKeys(): String

  companion object {
    init {
      System.loadLibrary("kotlin-jni")
    }
  }

}