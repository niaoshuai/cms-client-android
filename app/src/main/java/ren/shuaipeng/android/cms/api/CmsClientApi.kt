package ren.shuaipeng.android.cms.api

import androidx.lifecycle.LiveData
import ren.shuaipeng.android.cms.entity.Post
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 定义API接口
 */
interface CmsClientApi {

    @GET("/post/{id}")
    fun postDetail(@Path("id") id: String): LiveData<ApiResponse<Post>>


//    companion object {
//        private const val BASE_URL = "http://192.168.11.230:8080/"
//        private const val BASE_MOCK_URL = "http://localhost:"
//        fun createMock(port: Int): CmsClientApi = create(HttpUrl.parse("$BASE_MOCK_URL$port")!!)
//        fun create(): CmsClientApi = create(HttpUrl.parse(BASE_URL)!!)
//        fun create(httpUrl: HttpUrl): CmsClientApi {
//            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
//            logger.level = HttpLoggingInterceptor.Level.BASIC
//
//            val client = OkHttpClient.Builder()
//                .addInterceptor(logger)
//                .build()
//            return Retrofit.Builder()
//                .baseUrl(httpUrl)
//                .client(client)
////                .addCallAdapterFactory(Rx)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(CmsClientApi::class.java)
//        }
//    }
}
