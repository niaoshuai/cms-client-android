package ren.shuaipeng.android.cms.api

import org.junit.Assert.assertNotNull
import org.junit.Test

class CmsClientApiTest {

    @Test
    fun testDetail() {
        val api = CmsClientApi.create()
        var item = api.detail("14").execute()

        assertNotNull(item)
        assertNotNull(item.body())
    }
}
