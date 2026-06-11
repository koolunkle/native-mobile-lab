package com.example.popularmovies.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.popularmovies.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: List<MovieEntity>)

    /*@Query("SELECT * FROM movies")
    fun getMovies(): List<MovieEntity>*/

    /*
    @Query("SELECT * FROM movies")
    fun getMovies(): PagingSource<Int, MovieEntity>
    */

    @Query("SELECT * FROM movies WHERE releaseDate LIKE :year || '%' ORDER BY popularity DESC")
    fun getMovies(year: String): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM movies")
    suspend fun clearMovies()
}