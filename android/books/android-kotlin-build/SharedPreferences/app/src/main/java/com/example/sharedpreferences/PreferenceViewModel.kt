package com.example.sharedpreferences

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class PreferenceViewModel(private val preferenceWrapper: PreferenceWrapper) : ViewModel() {

    fun saveText(text: String) {
        preferenceWrapper.saveText(text)
    }

    fun getText(): LiveData<String> {
        return preferenceWrapper.getText()
    }

    override fun onCleared() {
        preferenceWrapper.clear()
        super.onCleared()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PreferenceApplication)
                PreferenceViewModel(application.preferenceWrapper)
            }
        }
    }
}