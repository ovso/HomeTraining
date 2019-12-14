package io.github.ovso.hometraining.data.api

import com.google.gson.JsonElement
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchService {
  @GET("youtube/v3/search")
  fun search(@QueryMap queryMap: Map<String, @JvmSuppressWildcards Any>): Single<JsonElement>

  @GET("youtube/v3/search")
  suspend fun searchCoroutine(@QueryMap queryMap: Map<String, @JvmSuppressWildcards Any>): Response<JsonElement>
}