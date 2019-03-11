package ren.shuaipeng.android.samples.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ren.shuaipeng.android.samples.entity.Post


interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts : List<Post>)
}