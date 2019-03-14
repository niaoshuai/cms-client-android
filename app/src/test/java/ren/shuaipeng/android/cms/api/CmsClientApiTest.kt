package ren.shuaipeng.android.cms.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ren.shuaipeng.android.cms.entity.Post
import ren.shuaipeng.android.cms.kit.LiveDataCallAdapterFactory
import ren.shuaipeng.android.cms.kit.LiveDataKit.getValue
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class CmsClientApiTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var api: CmsClientApi

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(CmsClientApi::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Post API Test`() {
        val post = Post("12", "测试", LocalDateTime.now(), LocalDateTime.now(), "", "")
        //定义MockServer响应
        enqueueResponse(post)
        // 发起请求
        val postResp = (getValue(api.postDetail("12")) as ApiSuccessResponse).body
        // 记录MockServer请求
        val request = mockWebServer.takeRequest()
        //验证记录的请求
        assertThat(request.path, `is`("/post/12"))
        assertThat<Post>(postResp, notNullValue())
        assertThat(postResp.id, `is`("12"))
        assertThat(postResp.title, `is`("测试"))
    }

    private fun enqueueResponse(post: Post, headers: Map<String, String> = emptyMap()) {
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(Gson().toJson(post))
        )
    }

//    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
//        val inputStream = javaClass.classLoader
//            .getResourceAsStream("api-response/$fileName")
//        val source = Okio.buffer(Okio.source(inputStream))
//        val mockResponse = MockResponse()
//        for ((key, value) in headers) {
//            mockResponse.addHeader(key, value)
//        }
//        mockWebServer.enqueue(
//            mockResponse
//                .setBody(source.readString(Charsets.UTF_8))
//        )
//    }
}