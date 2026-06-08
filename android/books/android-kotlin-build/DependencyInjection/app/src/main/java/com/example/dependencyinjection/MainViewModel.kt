package com.example.dependencyinjection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dependencyinjection.repository.NumberRepository

class MainViewModel(private val numberRepository: NumberRepository) : ViewModel() {

    private val _numberLiveData: MutableLiveData<Int> = MutableLiveData<Int>()
    val numberLiveData: LiveData<Int> get() = _numberLiveData

    fun generateNextNumber() {
        _numberLiveData.postValue(numberRepository.generateNextNumber())
    }
}