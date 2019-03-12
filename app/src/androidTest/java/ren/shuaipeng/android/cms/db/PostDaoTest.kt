package ren.shuaipeng.android.cms.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ren.shuaipeng.android.cms.entity.Post
import java.util.*

@RunWith(AndroidJUnit4::class)
class PostDaoTest {

    private lateinit var db: CmsClientDb

    @Before
    fun init() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = CmsClientDb.create(appContext, true)
    }

    @After
    fun close() {
        db.close()
    }

    @Test
    fun testInsert() {
        //新增
        val post1 = Post("1", "测试", "niaoshuai", System.currentTimeMillis(), System.currentTimeMillis(), null, "")
        db.posts().insert(Arrays.asList(post1))
        //查询
        val dbPost1 = db.posts().findById("1")
        //匹配
        assertEquals(dbPost1.id, post1.id)
    }
}
