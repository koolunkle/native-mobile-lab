package com.example.datastore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsStore: SettingsStore) : ViewModel() {

    private val _textLiveData: MutableLiveData<String> = MutableLiveData<String>()
    val textLiveData: LiveData<String> get() = _textLiveData

    init {
        viewModelScope.launch {
            settingsStore.text.collect {
                _textLiveData.value = it
            }
        }
    }

    fun saveText(text: String) {
        viewModelScope.launch {
            settingsStore.saveText(text)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SettingsApplication)
                SettingsViewModel(application.settingsStore)
            }
        }
    }
}