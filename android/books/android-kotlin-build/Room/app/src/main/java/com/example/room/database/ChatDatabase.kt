package com.example.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.room.DateConverter
import com.example.room.dao.MessageDao
import com.example.room.dao.UserDao
import com.example.room.entity.Message
import com.example.room.entity.User

@Database(entities = [User::class, Message::class], version = 2)
@TypeConverters(DateConverter::class)
abstract class ChatDatabase : RoomDatabase() {

    companion object {
        private lateinit var chatDatabase: ChatDatabase

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE messages ADD COLUMN status INTEGER")
            }
        }

        fun getDatabase(applicationContext: Context): ChatDatabase {
            if (!(::chatDatabase.isInitialized)) {
                chatDatabase =
                    Room.databaseBuilder(applicationContext, chatDatabase::class.java, "chat-db")
                        .addMigrations(MIGRATION_1_2)
                        .build()
            }
            return chatDatabase
        }
    }

    abstract fun userDao(): UserDao
    abstract fun messageDao(): MessageDao
}