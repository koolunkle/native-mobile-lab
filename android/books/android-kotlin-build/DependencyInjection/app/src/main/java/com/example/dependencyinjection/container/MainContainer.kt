package com.example.dependencyinjection.container

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dependencyinjection.MainViewModel
import com.example.dependencyinjection.repository.NumberRepository

class MainContainer(private val numberRepository: NumberRepository) {

    fun getMainViewModelFactory(): ViewModelProvider.Factory {
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