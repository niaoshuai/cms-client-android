package ren.shuaipeng.android.cms.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "post")
data class Post(
    @PrimaryKey
    val id: String,
    val title: String,
    @ColumnInfo(name = "gmt_create")
    val gmtCreate: LocalDateTime?,
    @ColumnInfo(name = "gmt_modified")
    val gmtModified: LocalDateTime?,
    val thumbnail: String?,
    val url: String?
)