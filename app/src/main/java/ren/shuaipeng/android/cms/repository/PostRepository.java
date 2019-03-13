package ren.shuaipeng.android.cms.repository;

import androidx.lifecycle.LiveData;
import ren.shuaipeng.android.cms.api.CmsClientApi;
import ren.shuaipeng.android.cms.db.PostDao;
import ren.shuaipeng.android.cms.entity.Post;
import retrofit2.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.Executor;

import static ren.shuaipeng.android.cms.AppContant.FRESH_TIMEOUT;

@Singleton
public class PostRepository {

    private final CmsClientApi cmsClientApi;
    private final PostDao postDao;
    private final Executor executor;

    @Inject
    public PostRepository(CmsClientApi cmsClientApi, PostDao postDao, Executor executor) {
        this.cmsClientApi = cmsClientApi;
        this.postDao = postDao;
        this.executor = executor;
    }


    public LiveData<Post> findById(String id) {
        refreshPost(id);
//        // Returns a LiveData object directly from the database.
        return postDao.findById(id);
    }

    private void refreshPost(final String id) {
        // Runs in a background thread.
        executor.execute(() -> {
            // Check if user data was fetched recently.
            boolean userExists = postDao.hasPost(LocalDateTime.now().minusMinutes(FRESH_TIMEOUT), LocalDateTime.now().minusMinutes(-FRESH_TIMEOUT));
            if (!userExists) {
                // Refreshes the data.
                Response<Post> response = null;
                try {
                    response = cmsClientApi.postDetail(id).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Check for errors here.

                // Updates the database. The LiveData object automatically
                // refreshes, so we don't need to do anything else here.
                postDao.insert(Arrays.asList(response.body()));
            }
        });
    }

}
