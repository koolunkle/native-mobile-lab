package com.example.room

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun from(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun to(date: Date?): Long? {
        return date?.time
    }
}