package com.example.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.room.entity.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // suspend fun insertUser(user: User)
    fun insertUser(user: User): Completable

    @Update
    // suspend fun updateUser(user: User)
    fun updateUser(user: User): Completable

    @Delete
    // suspend fun deleteUser(user: User)
    fun deleteUser(user: User): Completable

    @Query("SELECT * FROM users")
    // fun loadAllUsers(): LiveData<List<User>>
    fun loadAllUsers(): Flowable<List<User>>
}