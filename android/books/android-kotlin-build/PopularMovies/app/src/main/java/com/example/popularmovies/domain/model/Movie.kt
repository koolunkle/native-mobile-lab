package com.example.popularmovies.domain.model

import java.io.Serializable

data class Movie(
    val id: Int = 0,
    val title: String = "",
    val posterPath: String = "",
    val backdropPath: String = "",
    val overview: String = "",
    val releaseDate: String = "",
    val voteAverage: Float = 0f,
    val popularity: Float = 0f
) : Serializable {
    val posterUrl: String get() = "https://image.tmdb.org/t/p/w185/$posterPath"
    val releaseYear: String get() = releaseDate.take(4)
}