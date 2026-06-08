package com.example.sharedviewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class TotalsViewModel : ViewModel() {

    /*var total = 0

    fun increaseTotal(): Int {
        total++
        return total
    }*/

    /*private val _total: MutableLiveData<Int> = MutableLiveData<Int>()
    val total: LiveData<Int> get() = _total*/

    /*private val _total: MutableStateFlow<Int> = MutableStateFlow(0)
    val total: StateFlow<Int> get() = _total*/

    private val _total = BehaviorSubject.createDefault(0)
    val total: Observable<Int> get() = _total

    /*init {
        _total.postValue(0)
    }*/

    fun increaseTotal() {
        // _total.postValue((_total.value ?: 0) + 1)
        // _total.value += 1
        _total.onNext((_total.value ?: 0) + 1)
    }
}