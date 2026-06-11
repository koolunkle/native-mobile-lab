package com.example.popularmovies.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.popularmovies.domain.model.Movie
import com.example.popularmovies.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
) : ViewModel() {

    // val popularMovies: LiveData<List<Movie>> = getPopularMoviesUseCase()

    /*private val _popularMovies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    val popularMovies: StateFlow<List<Movie>> get() = _popularMovies*/

    // val error: LiveData<String> = movieRepository.error

    /*private val _error: MutableSharedFlow<String> = MutableStateFlow("")
    val error: SharedFlow<String> get() = _error*/

    /*private val _uiState: MutableStateFlow<MovieUiState> = MutableStateFlow(MovieUiState.Loading)
    val uiState: StateFlow<MovieUiState> get() = _uiState*/

    /*init {
        fetchPopularMovies()
    }*/

    /*private fun fetchPopularMovies() {
        viewModelScope.launch {
            _uiState.value = MovieUiState.Loading
            getPopularMoviesUseCase().catch {
                    // _error.emit("An exception occurred:${it.message}")
                    _uiState.value = MovieUiState.Error("An exception occurred:${it.message}")
                }.collect {
                    // _popularMovies.value = it
                    _uiState.value = MovieUiState.Success(it)
                }
        }
    }*/

    /*companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication
                MovieViewModel(
                    getPopularMoviesUseCase = application.getPopularMoviesUseCase
                )
            }
        }
    }*/

    val popularMovies: Flow<PagingData<Movie>> = getPopularMoviesUseCase().cachedIn(viewModelScope)
}