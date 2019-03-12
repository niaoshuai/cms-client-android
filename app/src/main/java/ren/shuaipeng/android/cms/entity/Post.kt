package ren.shuaipeng.android.cms.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "post")
data class Post(
    @PrimaryKey
    val id: String,
    val title: String,
    val gmtCreate: Date?,
    val gmtModified: Date?,
    val thumbnail: String?,
    val url: String?
)