package ren.shuaipeng.android.cms.db

import androidx.room.TypeConverter

import java.util.Date

class DateConvert {

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}
