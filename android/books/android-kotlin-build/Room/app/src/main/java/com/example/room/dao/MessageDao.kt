package com.example.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.room.entity.Message

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessages(vararg messages: Message)

    @Update
    fun updateMessages(vararg messages: Message)

    @Delete
    fun deleteMessages(vararg messages: Message)

    @Query("SELECT * FROM messages")
    fun loadAllMessages(): List<Message>

    @Query("SELECT * FROM messages WHERE user=:userId AND time>=:time")
    fun loadMessagesFromUserAfterTime(userId: String, time: Long): List<Message>

    /*@Query("SELECT * FROM messages WHERE user IN (:userId) AND time>=:time")
    fun loadMessagesFromUserAfterTime(userId: String, time: Long): List<Message>*/
}