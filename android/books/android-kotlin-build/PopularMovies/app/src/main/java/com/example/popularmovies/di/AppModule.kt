package com.example.popularmovies.di

import android.content.Context
import com.example.popularmovies.BuildConfig
import com.example.popularmovies.data.repository.MovieRepositoryImpl
import com.example.popularmovies.data.source.local.MovieDao
import com.example.popularmovies.data.source.local.MovieDatabase
import com.example.popularmovies.data.source.remote.MovieService
import com.example.popularmovies.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Module
    @InstallIn(SingletonComponent::class)
    object ProviderModule {

        @Provides
        @Singleton
        fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
            return MovieDatabase.getInstance(context)
        }

        @Provides
        fun provideMovieDao(database: MovieDatabase): MovieDao {
            return database.movieDao()
        }

        @Provides
        @Singleton
        fun provideMovieService(): MovieService {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(MovieService::class.java)
        }

        @Provides
        fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
    }
}
