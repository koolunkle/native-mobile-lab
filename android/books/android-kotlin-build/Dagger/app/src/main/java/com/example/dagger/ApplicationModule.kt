package com.example.dagger

import com.example.dagger.repository.NumberRepository
import com.example.dagger.repository.NumberRepositoryImpl
import dagger.Module
import dagger.Provides
import java.util.Random

@Module
class ApplicationModule {

    @Provides
    fun provideRandom(): Random = Random()

    @Provides
    fun provideNumberRepository(random: Random): NumberRepository = NumberRepositoryImpl(random)
}