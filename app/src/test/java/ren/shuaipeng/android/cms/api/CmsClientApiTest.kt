package ren.shuaipeng.android.cms.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ren.shuaipeng.android.cms.kit.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    fun getUser() {
        enqueueResponse("user-yigit.json")
//        val yigit = (getValue(service.getUser("yigit")) as ApiSuccessResponse).body
//
//        val request = mockWebServer.takeRequest()
//        assertThat(request.path, `is`("/users/yigit"))
//
//        assertThat<User>(yigit, notNullValue())
//        assertThat(yigit.avatarUrl, `is`("https://avatars3.githubusercontent.com/u/89202?v=3"))
//        assertThat(yigit.company, `is`("Google"))
//        assertThat(yigit.blog, `is`("birbit.com"))
    }


    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
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
    }
}