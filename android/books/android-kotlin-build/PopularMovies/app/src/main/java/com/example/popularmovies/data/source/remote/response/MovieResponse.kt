package com.example.popularmovies.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    val page: Int, @field:Json(name = "results") val results: List<MovieDto>
)

@JsonClass(generateAdapter = true)
data class MovieDto(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val id: Int = 0,
    @field:Json(name = "original_language") val originalLanguage: String = "",
    @field:Json(name = "original_title") val originalTitle: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    @field:Json(name = "poster_path") val posterPath: String = "",
    @field:Json(name = "release_date") val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    @field:Json(name = "vote_average") val voteAverage: Float = 0f,
    @field:Json(name = "vote_count") val voteCount: Int = 0
)