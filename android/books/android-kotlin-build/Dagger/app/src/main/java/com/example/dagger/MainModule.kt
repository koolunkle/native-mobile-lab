package com.example.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dagger.repository.NumberRepository
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideMainViewModelFactory(numberRepository: NumberRepository): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                    modelClass.cast(MainViewModel(numberRepository))!!
                } else {
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            }
        }
    }
}