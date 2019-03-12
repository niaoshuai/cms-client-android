package ren.shuaipeng.android.cms.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ren.shuaipeng.android.cms.entity.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts : List<Post>)

    @Query("SELECT * FROM post WHERE id = :id")
    fun findById(id: String): Post

}