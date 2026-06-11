package com.example.popularmovies.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.popularmovies.data.source.local.entity.MovieEntity
import com.example.popularmovies.data.source.local.entity.RemoteKeys

@Database(entities = [MovieEntity::class, RemoteKeys::class], version = 2)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "CREATE TABLE IF NOT EXISTS `remote_keys` (" +
                            "`movieId` INTEGER NOT NULL, " +
                            "`prevKey` INTEGER, " +
                            "`nextKey` INTEGER, " +
                            "PRIMARY KEY(`movieId`))"
                )
            }
        }

        fun getInstance(context: Context): MovieDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MovieDatabase {
            return Room.databaseBuilder(context, MovieDatabase::class.java, "movie-db")
                // .fallbackToDestructiveMigration(false)
                .addMigrations(MIGRATION_1_2)
                .build()
        }
    }
}