package ren.shuaipeng.android.cms.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import ren.shuaipeng.android.cms.db.LiveDataTestUtil.getValue
import ren.shuaipeng.android.cms.entity.Post
import java.time.LocalDateTime

class PostDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoad() {
        val post = Post("1", "X", LocalDateTime.now(), LocalDateTime.now(), null, null)
        db.postDao().insert(post)

        val loaded = getValue(db.postDao().findById(post.id))
        assertThat(loaded.title, `is`("X"))
    }
}