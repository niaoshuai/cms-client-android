package ren.shuaipeng.android.cms.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ren.shuaipeng.android.cms.entity.Post

@Database(
    entities = arrayOf(Post::class),
    version = 1,
    exportSchema = false
)
abstract class CmsClientDb : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory : Boolean): CmsClientDb {
            val databaseBuilder = if(useInMemory) {
                Room.inMemoryDatabaseBuilder(context, CmsClientDb::class.java)
            } else {
                Room.databaseBuilder(context, CmsClientDb::class.java, "reddit.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun posts(): PostDao
}