package ren.shuaipeng.android.cms.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class Post(
    @PrimaryKey
    val id: String,
    val title: String,

//    val gmtCreate: LocalDateTime,
//    val gmtModified: LocalDateTime,
    val thumbnail: String?,
    val url: String?
)