package io.github.ovso.hometraining

import com.google.gson.JsonElement
import io.github.ovso.hometraining.data.api.SearchRequest
import io.github.ovso.hometraining.data.model.Video
import io.github.ovso.hometraining.utils.TestSchedulerProvider
import org.junit.Test
import retrofit2.HttpException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HomeTrainingUnitTest {
    private val searchRequest = SearchRequest()

    @Test
    fun search_request() {

        fun getApiKey(): String {
            val keys =
                "AIzaSyA4pdIQO-74kZv7MLpPZs13oEYq2w5ki4E//AIzaSyCDlPMTU-TsKp8k7t6875jkAIRWrl2XCfE//AIzaSyCe8fJ3dw_8YzFq1L7X3Iip9Bs_KZ66bNM//AIzaSyBT2wy_F43ouGtgmNBmklik6qYHYFIVtbA"
            return keys.split("//")[0]
        }

        fun getParams() = mapOf(
            "q" to "홈트레이닝",
            "maxResults" to 1,
            "order" to "viewCount",
            "type" to "video",
            "videoSyndicated" to "any",
            "key" to getApiKey(),
            "part" to "snippet",
            "fields" to "items(id,snippet(title,thumbnails(medium)))"
        )

        fun onSuccess(it: JsonElement) {
            println("검색 성공 <<<<")
        }

        fun onFailure(it: Throwable) {
            println("검색 실패 <<<<")
            println(it)
            println((it as? HttpException)?.response()?.errorBody()?.string())
        }

        searchRequest.api()
            .search(getParams())
            .subscribeOn(TestSchedulerProvider.io())
            .observeOn(TestSchedulerProvider.ui())
            .subscribe(::onSuccess, ::onFailure)
    }

    @Test
    fun search_request2() {

        fun getApiKey(): String {
            val keys =
                "AIzaSyA4pdIQO-74kZv7MLpPZs13oEYq2w5ki4E//AIzaSyCDlPMTU-TsKp8k7t6875jkAIRWrl2XCfE//AIzaSyCe8fJ3dw_8YzFq1L7X3Iip9Bs_KZ66bNM//AIzaSyBT2wy_F43ouGtgmNBmklik6qYHYFIVtbA"
            return keys.split("//")[0]
        }

        fun onSuccess(it: Video) {
            println("onSuccess = $it")
        }

        fun onFailure(it: Throwable) {
            println(it.message)
            println((it as? HttpException)?.response()?.errorBody()?.string())
        }

        fun getParams() = mapOf(
            "q" to "인기 홈트레이닝",
            "maxResults" to 1,
            "order" to "viewCount",
            "type" to "video",
            "videoSyndicated" to "any",
            "key" to getApiKey(),
            "part" to "snippet",
            "fields" to "items(id,snippet(title,thumbnails(medium)))"
        )

        searchRequest.api()
            .search2(getParams())
            .subscribeOn(TestSchedulerProvider.io())
            .observeOn(TestSchedulerProvider.ui())
            .subscribe(::onSuccess, ::onFailure)
    }
}
