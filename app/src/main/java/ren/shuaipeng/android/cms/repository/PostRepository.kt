package ren.shuaipeng.android.cms.repository

import androidx.lifecycle.MutableLiveData
import ren.shuaipeng.android.cms.AppContant.FRESH_TIMEOUT
import ren.shuaipeng.android.cms.api.CmsClientApi
import ren.shuaipeng.android.cms.db.PostDao
import ren.shuaipeng.android.cms.entity.Post
import retrofit2.Response
import java.io.IOException
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject
constructor(private val cmsClientApi: CmsClientApi, private val postDao: PostDao, private val executor: Executor) {

    fun findById(id: String) {
        refreshPost(id)
        // Returns a LiveData object directly from the database.
        val data = MutableLiveData<Post>()
        return data.postValue(postDao.findById(id))
    }

    private fun refreshPost(id: String) {
        // Runs in a background thread.
        executor.execute {
            // Check if user data was fetched recently.
            val userExists = postDao.hasPost(
                LocalDateTime.now().minusMinutes(FRESH_TIMEOUT.toLong()),
                LocalDateTime.now().minusMinutes((-FRESH_TIMEOUT).toLong())
            )
            if (!userExists) {
                // Refreshes the data.
                var response: Response<Post>? = null
                try {
                    response = cmsClientApi.postDetail(id).execute()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                // Check for errors here.

                // Updates the database. The LiveData object automatically
                // refreshes, so we don't need to do anything else here.
                postDao.insert(Arrays.asList<Post>(response!!.body()))
            }
        }
    }

}
