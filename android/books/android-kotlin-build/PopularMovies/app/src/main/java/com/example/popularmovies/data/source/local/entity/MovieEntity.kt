package com.example.popularmovies.data.source.local.entity

import androidx.room.Entity

@Entity(tableName = "movies", primaryKeys = [("id")])
data class MovieEntity(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val id: Int = 0,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Float = 0f,
    val voteCount: Int = 0,
    val cachedAt: Long = System.currentTimeMillis()
)
