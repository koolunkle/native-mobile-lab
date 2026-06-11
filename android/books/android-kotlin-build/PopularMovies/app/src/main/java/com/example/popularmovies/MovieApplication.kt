package com.example.popularmovies

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MovieApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    // lateinit var movieRepository: MovieRepositoryImpl

    // lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    override fun onCreate() {
        super.onCreate()

        /*val moshi = Moshi.Builder()
            add(KotlinJsonAdapterFactory())
            .build()*/

        /*val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()*/

        // val movieService = retrofit.create(MovieService::class.java)
        // val movieDatabase = MovieDatabase.getInstance(applicationContext)

        // movieRepository = MovieRepositoryImpl(movieService, movieDatabase)
        // getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)

        /*val constrains = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()*/

        /*val workRequest = PeriodicWorkRequest.Builder(MovieWorker::class.java, 1, TimeUnit.HOURS)
            .setConstraints(constrains)
            .addTag("movie-work")
            .build()*/

        // WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }
}