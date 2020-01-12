package io.github.ovso.hometraining.data.api

import com.google.gson.JsonElement
import io.github.ovso.hometraining.data.model.Video
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchService {
    @GET("youtube/v3/search")
    fun search(@QueryMap queryMap: Map<String, @JvmSuppressWildcards Any>): Single<Video>

    @GET("youtube/v3/channels")
    fun channels(@QueryMap queryMap: Map<String, @JvmSuppressWildcards Any>): Single<JsonElement>

    @GET("youtube/v3/search")
    suspend fun searchCoroutine(@QueryMap queryMap: Map<String, @JvmSuppressWildcards Any>): Response<JsonElement>
}
