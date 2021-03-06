package io.github.ovso.hometraining

import io.github.ovso.hometraining.data.api.SearchRequest
import io.github.ovso.hometraining.data.model.Video
import io.github.ovso.hometraining.utils.TestSchedulerProvider
import io.github.ovso.hometraining.view.ui.video.model.VideoItem
import okhttp3.internal.immutableListOf
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
            .search(getParams())
            .subscribeOn(TestSchedulerProvider.io())
            .observeOn(TestSchedulerProvider.ui())
            .subscribe(::onSuccess, ::onFailure)
    }

    @Test
    fun search_request_ads() {

        fun getApiKey(): String {
            val keys =
                "AIzaSyA4pdIQO-74kZv7MLpPZs13oEYq2w5ki4E//AIzaSyCDlPMTU-TsKp8k7t6875jkAIRWrl2XCfE//AIzaSyCe8fJ3dw_8YzFq1L7X3Iip9Bs_KZ66bNM//AIzaSyBT2wy_F43ouGtgmNBmklik6qYHYFIVtbA"
            return keys.split("//")[0]
        }

        fun onSuccess(it: List<VideoItem>) {
            println("성공")
            println("onSuccess = ${it.size}")
        }

        fun onFailure(it: Throwable) {
            println("실패")
            println(it)
            println(it.message)
            println((it as? HttpException)?.response()?.errorBody()?.string())
        }

        fun getParams() = mapOf(
            "q" to "홈트레이닝",
            "maxResults" to 6,
            "order" to "viewCount",
            "type" to "video",
            "videoSyndicated" to "any",
            "key" to getApiKey(),
            "part" to "snippet",
            "fields" to "items(id,snippet(title,thumbnails(medium)))"
        )

        searchRequest.api()
            .search(getParams())
            .map {
                searchRequest.toVideoItems(it, 5)
            }
            .subscribeOn(TestSchedulerProvider.io())
            .observeOn(TestSchedulerProvider.ui())
            .subscribe(::onSuccess, ::onFailure)
    }

    @Test
    fun insertAdsLoopTest() {
        val originItems =
            immutableListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11")
        val newItems = mutableListOf<String>()
        val count = originItems.count()
        for (i in 0 until count step 5) {
            val toIndex = if (i + 5 > count) count else i + 5
            println(originItems.subList(i, toIndex))
            newItems.add("ㅋ")
            newItems.addAll(originItems.subList(i, toIndex))
            println(i)
        }
        val newItems2 = ArrayList(newItems)
        println(newItems2)
    }
}
