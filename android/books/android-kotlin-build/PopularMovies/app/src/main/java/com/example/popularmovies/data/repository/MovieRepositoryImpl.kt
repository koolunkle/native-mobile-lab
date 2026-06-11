package com.example.popularmovies.data.repository

// Paging 3 Imports
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.popularmovies.BuildConfig
import com.example.popularmovies.data.mapper.toDomain
import com.example.popularmovies.data.source.local.MovieDatabase
import com.example.popularmovies.data.source.remote.MovieRemoteMediator
import com.example.popularmovies.data.source.remote.MovieService
import com.example.popularmovies.domain.model.Movie
import com.example.popularmovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val movieDatabase: MovieDatabase,
    // private val dispatcher: CoroutineDispatcher
) : MovieRepository {

    // private val apiKey = BuildConfig.TMDB_API_KEY

    /*
    private val movieLiveData: MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = movieLiveData

    private val errorLiveData: MutableLiveData<String> = MutableLiveData<String>()
    val error: LiveData<String> get() = errorLiveData

    suspend fun fetchMovies() {
        try {
            val popularMovies = movieService.getPopularMovies(apiKey)
            movieLiveData.postValue(popularMovies.results)
        } catch (exception: Exception) {
            errorLiveData.postValue("An error occurred: ${exception.message}")
        }
    }
    */

    /*
    override fun fetchMovies(): Flow<List<Movie>> {
        return flow {
            emit(movieService.getPopularMovies(apiKey, 1).results)
        }.flowOn(dispatcher)
    }
    */

    /*
    override fun fetchMovies(): Flow<List<Movie>> {
        return flow {
            val movieDao: MovieDao = movieDatabase.movieDao()
            val savedMovies = movieDao.getMovies()
            val isCacheExpired = savedMovies.firstOrNull()?.let {
                System.currentTimeMillis() - it.cachedAt > 60 * 60 * 1000 // 1 hour
            } ?: true

            if (savedMovies.isEmpty() || isCacheExpired) {
                try {
                    val moviesDto = movieService.getPopularMovies(apiKey, 1).results
                    val movieEntities = moviesDto.map { it.toEntity() }
                    movieDao.addMovies(movieEntities)
                    emit(movieEntities.map { it.toDomain() })
                } catch (e: Exception) {
                    if (savedMovies.isNotEmpty()) {
                        emit(savedMovies.map { it.toDomain() })
                    } else {
                        throw e
                    }
                }
            } else {
                emit(savedMovies.map { it.toDomain() })
            }
        }.flowOn(dispatcher)
    }
    */

    @OptIn(ExperimentalPagingApi::class)
    override fun fetchMovies(year: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = MovieRemoteMediator(
                movieDatabase,
                movieService
            ),
            pagingSourceFactory = {
                movieDatabase.movieDao().getMovies(year)
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override suspend fun fetchMoviesFromNetwork() {
        /*
        val movieDao: MovieDao = movieDatabase.movieDao()
        try {
            val moviesDto = movieService.getPopularMovies(apiKey, 1).results
            val movieEntities = moviesDto.map { it.toEntity() }
            movieDao.addMovies(movieEntities)
        } catch (exception: Exception) {
            Log.d("MovieRepository", "An error occurred:${exception.message}")
        }
        */
    }
}
