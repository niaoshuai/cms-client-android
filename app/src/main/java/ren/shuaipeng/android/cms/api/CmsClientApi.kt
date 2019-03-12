package ren.shuaipeng.android.cms.api

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.runner.RunWith
import ren.shuaipeng.android.cms.entity.Post
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 定义API接口
 */
@RunWith(AndroidJUnit4::class)
interface CmsClientApi {

    @GET("/post/{id}")
    fun detail(@Path("id") id: String): Call<Post>


    companion object {
        private const val BASE_URL = "http://192.168.11.230:8080/"
        fun create(): CmsClientApi = create(HttpUrl.parse(BASE_URL)!!)
        fun create(httpUrl: HttpUrl): CmsClientApi {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("API", it)
            })
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(client)
//                .addCallAdapterFactory(Rx)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CmsClientApi::class.java)
        }
    }
}
