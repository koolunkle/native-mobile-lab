package com.example.popularmovies.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.popularmovies.domain.repository.MovieRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class MovieWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val movieRepository: MovieRepository
) : CoroutineWorker(context, params) {

    /*override fun doWork(): Result {
        val movieRepository = (context as MovieApplication).movieRepository

        CoroutineScope(Dispatchers.IO).launch {
            movieRepository.fetchMoviesFromNetwork()
        }

        return Result.success()
    }*/

    override suspend fun doWork(): Result {
        return try {
            // val movieRepository = (applicationContext as MovieApplication).movieRepository
            movieRepository.fetchMoviesFromNetwork()

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}