package ren.shuaipeng.android.cms.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import ren.shuaipeng.android.cms.api.CmsClientApi
import ren.shuaipeng.android.cms.db.PostDao
import ren.shuaipeng.android.cms.util.InstantAppExecutors

@RunWith(MockitoJUnitRunner::class)
class PostRepositoryTest {

    private val postDao = mock(PostDao::class.java)
    private val cmsClientApi = mock(CmsClientApi::class.java)
    private val postRepository = PostRepository(cmsClientApi, postDao, InstantAppExecutors())

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun findById() {
        postRepository!!.findById("1")
        verify(postDao).findById("1")
    }
}