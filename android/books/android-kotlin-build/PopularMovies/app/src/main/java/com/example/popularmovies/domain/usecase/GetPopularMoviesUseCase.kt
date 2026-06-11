package com.example.popularmovies.domain.usecase

import androidx.paging.PagingData
import com.example.popularmovies.domain.model.Movie
import com.example.popularmovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
) {

    operator fun invoke(): Flow<PagingData<Movie>> {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString()

        /*
        return repository.fetchMovies().map { movies ->
            movies.filter { it.releaseDate.startsWith(currentYear) }
                .sortedByDescending { it.popularity }
        }
        */

        return repository.fetchMovies(currentYear)
    }
}
