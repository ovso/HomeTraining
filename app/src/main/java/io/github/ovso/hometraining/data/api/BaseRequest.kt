package io.github.ovso.hometraining.data.api

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        with(httpClient) {
            readTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            connectTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            followRedirects(false)
            addInterceptor {
                val original = it.request()
                val requestBuilder = original.newBuilder()
                    .header("Content-Type", "plain/text")
                val request = requestBuilder.build()
                it.proceed(request)
            }
            addNetworkInterceptor {
                val original = it.request()
                val requestBuilder = original.newBuilder()
                    .header("Content-Type", "plain/text")
                val request = requestBuilder.build()
                it.proceed(request)
            }
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
            })
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
        return httpClient.build()
    }

    companion object {
        const val TIMEOUT_SECONDS = 30
    }
}
