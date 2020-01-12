package io.github.ovso.hometraining

import io.github.ovso.hometraining.data.api.SearchRequest
import io.github.ovso.hometraining.data.model.Item
import io.github.ovso.hometraining.data.model.Video
import io.github.ovso.hometraining.utils.TestSchedulerProvider
import org.jetbrains.anko.collections.forEachByIndex
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

        fun onSuccess(it: Video) {
            println("성공")
            println("onSuccess = $it")
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
                val mutableList = mutableListOf<Item>()
                it.items?.forEach { item ->
                    mutableList.add(item.copy())
                }

                for (i in 0 until mutableList.count() + 5 step 2) {
                    println(i)
                }

                it
            }
            .subscribeOn(TestSchedulerProvider.io())
            .observeOn(TestSchedulerProvider.ui())
            .subscribe(::onSuccess, ::onFailure)
    }

    @Test
    fun loop_test() {
        val originItems = mutableListOf("F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F")
        val customItems = mutableListOf<String>()
        val custom = "-"
//        println(originItems.count() + (originItems.count() / 1))
        for (i in 0 until (originItems.count() * 3)) {
            if (i % 3 == 0) {
                println(i)
                originItems.add(i, custom)
            }
        }

        println(originItems)
    }
}
