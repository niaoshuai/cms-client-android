package ren.shuaipeng.android.cms.repository

import androidx.lifecycle.LiveData
import ren.shuaipeng.android.cms.AppExecutors
import ren.shuaipeng.android.cms.api.CmsClientApi
import ren.shuaipeng.android.cms.db.PostDao
import ren.shuaipeng.android.cms.entity.Post
import ren.shuaipeng.android.cms.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject
constructor(
    private val cmsClientApi: CmsClientApi,
    private val postDao: PostDao,
    private val appExecutors: AppExecutors
) {

    fun findById(id: String): LiveData<Resource<Post>> {
        return object : NetworkBoundResource<Post, Post>(appExecutors) {
            override fun saveCallResult(item: Post) {
                postDao.insert(item)
            }

            override fun shouldFetch(data: Post?) = data == null

            override fun loadFromDb() = postDao.findById(id)

            override fun createCall() = cmsClientApi.postDetail(id)
        }.asLiveData()
    }

}
