package ren.shuaipeng.android.cms.api

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CmsClientApiTest {

    @get:Rule
    val mockWebServer = MockWebServer()

    private lateinit var api: CmsClientApi

    @Before
    fun `start mock`() {
        api = CmsClientApi.createMock(mockWebServer.port)
    }

    @After
    fun `close mock`() {
        mockWebServer.close()
    }

    @Test
    fun testDetail() {
        //模拟服务器的response
        mockWebServer.enqueue(MockResponse().setBody("{\"id\": \"14\",\"title\": \"XX\"}"))

        var item = api.postDetail("14").execute()
        assertNotNull(item)
        assertNotNull(item.body())
        assertNotNull(item.body()!!.id)
    }
}
