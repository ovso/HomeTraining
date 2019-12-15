package io.github.ovso.hometraining.data.api

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://www.googleapis.com"

abstract class BaseRequest2<T>(
  private val baseUrl: String,
  private val cls: Class<T>
) {

  fun api(): T = createRetrofit().create(cls)

  private fun createRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(createClient())
        .build()
  }

  private fun createClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.readTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
    httpClient.connectTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
    httpClient.followRedirects(false)
    httpClient.addInterceptor { chain ->
      val original = chain.request()
      val requestBuilder = original.newBuilder()
          .header("Content-Type", "plain/text")
      val request = requestBuilder.build()
      chain.proceed(request)
    }
    return httpClient.build()
  }

  companion object {
    const val TIMEOUT_SECONDS = 30
  }
}