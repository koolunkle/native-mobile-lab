package com.example.popularmovies.domain.repository

import androidx.paging.PagingData
import com.example.popularmovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    // fun fetchMovies(): Flow<List<Movie>>
    // fun fetchMovies(): Flow<PagingData<Movie>>

    fun fetchMovies(year: String): Flow<PagingData<Movie>>

    suspend fun fetchMoviesFromNetwork()
}