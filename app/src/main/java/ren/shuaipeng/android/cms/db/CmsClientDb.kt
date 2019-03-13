package ren.shuaipeng.android.cms.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ren.shuaipeng.android.cms.entity.Post

@Database(
    entities = arrayOf(Post::class),
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConvert::class)
abstract class CmsClientDb : RoomDatabase() {

    abstract fun postDao(): PostDao

}