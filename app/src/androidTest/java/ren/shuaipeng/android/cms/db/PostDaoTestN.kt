package ren.shuaipeng.android.cms.db

import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ren.shuaipeng.android.cms.entity.Post
import java.time.LocalDateTime
import java.util.*


/**
 * 另外的方式
 */
class PostDaoTestN {

    private lateinit var db: CmsClientDb

    @Before
    fun `init_db`() {
        db = CmsClientDb.create(getInstrumentation().context, true)
    }

    @After
    fun `close_db`() {
        db.close()
    }

    @Test
    fun `test_db_post_add`() {
        //新增
        val post = Post("1", "测试", LocalDateTime.now(), LocalDateTime.now(), null, null)
        db.posts().insert(Arrays.asList(post))
        //查询
        val dbPost = db.posts().findById("1")
        //匹配
        assertEquals(post.id, dbPost.id)
    }

    @Test
    fun `test_db_post_has`() {
        val now = LocalDateTime.now()
        val post1 = Post("1", "测试", now, now, null, null)
        db.posts().insert(Arrays.asList(post1))

        val r1 = db.posts().hasPost(now.minusMinutes(5L), now.minusMinutes(-5L))
        assertTrue(r1)

        val r2 = db.posts().hasPost(now.plusMinutes(5L), now.minusMinutes(-5L))
        assertFalse(r2)
    }
}
