package ren.shuaipeng.android.cms.db

import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ren.shuaipeng.android.cms.entity.Post
import java.time.LocalDateTime
import java.util.*

class PostDaoTest {

    private lateinit var db: CmsClientDb

    @Before
    fun init() {
        db = CmsClientDb.create(getInstrumentation().context, true)
    }

    @After
    fun close() {
        db.close()
    }

    @Test
    fun testInsert() {
        //新增
        val post1 = Post("1", "测试", LocalDateTime.now(), LocalDateTime.now(), null, null)
        db.posts().insert(Arrays.asList(post1))
        //查询
        val dbPost1 = db.posts().findById("1")
        //匹配
//        dbPost1.observe(,{
//            assertEquals(post1.id, it.id)
//        } )

    }

    @Test
    fun testHasPost() {
        val now = LocalDateTime.now()
        val post1 = Post("1", "测试", now, now, null, null)
        db.posts().insert(Arrays.asList(post1))

        val r1 = db.posts().hasPost(now.minusMinutes(5L), now.minusMinutes(-5L))
        assertTrue(r1)

        val r2 = db.posts().hasPost(now.plusMinutes(5L), now.minusMinutes(-5L))
        assertFalse(r2)
    }
}
