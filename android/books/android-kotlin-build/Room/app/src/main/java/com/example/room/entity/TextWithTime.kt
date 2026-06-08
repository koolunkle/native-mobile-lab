package com.example.room.entity

import androidx.room.ColumnInfo

data class TextWithTime(
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "time") val time: Long
)
