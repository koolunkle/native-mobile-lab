package com.example.popularmovies.data.mapper

import com.example.popularmovies.data.source.local.entity.MovieEntity
import com.example.popularmovies.data.source.remote.response.MovieDto
import com.example.popularmovies.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        popularity = popularity
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        popularity = popularity
    )
}

fun MovieDto.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        adult = adult,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        popularity = popularity,
        video = video,
        voteCount = voteCount,
        cachedAt = System.currentTimeMillis()
    )
}

fun Movie.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        popularity = popularity,
        cachedAt = System.currentTimeMillis()
    )
}
