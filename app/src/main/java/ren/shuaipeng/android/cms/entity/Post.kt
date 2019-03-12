package ren.shuaipeng.android.cms.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class Post(
    @PrimaryKey
    val id: String,
    val title: String,
    val author: String,
    val gmtCreate: Long,
    val gmtModified: Long,
    val thumbnail: String?,
    val url: String?
)