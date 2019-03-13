package ren.shuaipeng.android.cms.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ren.shuaipeng.android.cms.entity.Post
import java.time.LocalDateTime

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts : List<Post>)

    @Query("SELECT * FROM post WHERE id = :id")
    fun findById(id: String): Post

    @Query("SELECT 1 FROM post WHERE gmt_create between :start and :end")
    fun hasPost(start: LocalDateTime, end: LocalDateTime): Boolean

}