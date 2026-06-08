package com.example.dagger

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dagger.repository.NumberRepository

class MainViewModel(private val numberRepository: NumberRepository) : ViewModel() {

    private val _numberLiveData = MutableLiveData<Int>()
    val numberLiveData: LiveData<Int> = _numberLiveData

    fun generateNextNumber() {
        _numberLiveData.postValue(numberRepository.generateNextNumber())
    }
}