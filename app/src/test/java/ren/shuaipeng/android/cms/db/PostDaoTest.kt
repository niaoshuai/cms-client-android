package ren.shuaipeng.android.cms.db

import android.content.Context
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import ren.shuaipeng.android.cms.entity.Post
import java.time.LocalDateTime
import java.util.*


/**
 *TODO  此种MOCK方式会出现主线程异常（room）
 */
@RunWith(MockitoJUnitRunner::class)
class PostDaoTest {

    @Mock
    private lateinit var mockContext: Context

    private lateinit var db: CmsClientDb

    @Before
    fun init() {
        db = CmsClientDb.create(mockContext, true)
    }

    @After
    fun close() {
        db.close()
    }

    @Test
    fun testInsert() {
        //新增
        val post = Post("1", "测试", LocalDateTime.now(), LocalDateTime.now(), null, null)
        db.posts().insert(Arrays.asList(post))
        //查询
        val dbPost = db.posts().findById("1")
        //匹配
        assertEquals(post.id, dbPost.id)
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
