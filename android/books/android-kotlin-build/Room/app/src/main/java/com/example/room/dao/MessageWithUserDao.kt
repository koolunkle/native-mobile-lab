package com.example.room.dao

import androidx.room.Query
import com.example.room.entity.MessageWithUser

interface MessageWithUserDao {

    @Query("SELECT * FROM messages INNER JOIN users on users.user_id = messages.user")
    fun loadMessagesAndUsers(): List<MessageWithUser>
}