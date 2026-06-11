package com.example.popularmovies.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.popularmovies.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    /*private val _movie = MutableStateFlow(
        Movie(
            title = savedStateHandle.get<String>(DetailsActivity.EXTRA_TITLE).orEmpty(),
            releaseDate = savedStateHandle.get<String>(DetailsActivity.EXTRA_RELEASE).orEmpty().take(4),
            overview = savedStateHandle.get<String>(DetailsActivity.EXTRA_OVERVIEW).orEmpty(),
            posterPath = savedStateHandle.get<String>(DetailsActivity.EXTRA_POSTER).orEmpty(),
            id = -1,
            popularity = 0f,
            voteAverage = 0f,
            voteCount = 0
        )
    )*/

    private val _movie = MutableStateFlow(
        savedStateHandle.get<Movie>(DetailsActivity.EXTRA_MOVIE) ?: Movie()
    )
    val movie: StateFlow<Movie> = _movie.asStateFlow()
}
