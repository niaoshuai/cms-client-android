package ren.shuaipeng.android.samples.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey
    val id: String,
    val title: String,
    val score: Int,
    val author: String,
    val gmtCreate: Long,
    val gmtModified: Long,
    val thumbnail: String?,
    val url: String?
)