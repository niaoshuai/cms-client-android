package ren.shuaipeng.android.cms.db

import android.os.Build.VERSION_CODES.O
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class DateConvert {

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }


    @RequiresApi(O)
    @TypeConverter
    fun toDate_A8(timestamp: Long?): LocalDateTime? {
        val instant = Instant.ofEpochMilli(timestamp!!)
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    }

    @RequiresApi(O)
    @TypeConverter
    fun toTimestamp_A8(date: LocalDateTime?): Long? {
        return date!!.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }
}
