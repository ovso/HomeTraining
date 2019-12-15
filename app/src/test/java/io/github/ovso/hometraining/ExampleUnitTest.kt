package io.github.ovso.hometraining

import io.github.ovso.hometraining.data.api.SearchRequest2
import org.junit.Test
import retrofit2.HttpException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  private val searchRequest = SearchRequest2()

  @Test
  fun search_request() {

    fun getApiKey(): String {
      val keys =
        "AIzaSyA4pdIQO-74kZv7MLpPZs13oEYq2w5ki4E//AIzaSyCDlPMTU-TsKp8k7t6875jkAIRWrl2XCfE//AIzaSyCe8fJ3dw_8YzFq1L7X3Iip9Bs_KZ66bNM//AIzaSyBT2wy_F43ouGtgmNBmklik6qYHYFIVtbA"
      return keys.split("//")[0]
    }

    fun getQueryMap() = hashMapOf(
        "q" to "홈트레이닝",
        "maxResults" to 50,
        "order" to "viewCount",
        "type" to "video",
        "videoSyndicated" to "any",
        "key" to getApiKey(),
        "part" to "snippet",
        "fields" to "items(id,snippet(title,thumbnails(medium)))"
    )

    searchRequest.api()
        .search(getQueryMap())
        .subscribe(
            {
              //              println(it)
              println("검색 성공 <<<<")
            },
            {
              println((it as? HttpException)?.response()?.errorBody()?.string())
              println("검색 실패 <<<<")
            }
        )
  }
}
