package com.example.room.dao

import androidx.room.Query
import com.example.room.entity.TextWithTime

interface TextWithTimeDao {

    @Query("SELECT text, time FROM messages")
    fun loadTextsAndTimes(): List<TextWithTime>
}