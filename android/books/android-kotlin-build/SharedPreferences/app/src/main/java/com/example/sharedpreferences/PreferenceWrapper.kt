package com.example.sharedpreferences

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

const val KEY_TEXT = "keyText"

class PreferenceWrapper(private val sharedPreferences: SharedPreferences) {

    private val textLiveData = MutableLiveData<String>()

    private val preferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == KEY_TEXT) {
                textLiveData.postValue(sharedPreferences.getString(KEY_TEXT, ""))
            }
        }

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    fun clear() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    fun saveText(text: String) {
        /*sharedPreferences.edit()
            .putString(KEY_TEXT, text)
            .apply()*/
        sharedPreferences.edit {
            putString(KEY_TEXT, text)
        }
    }

    fun getText(): LiveData<String> {
        textLiveData.postValue(sharedPreferences.getString(KEY_TEXT, ""))
        return textLiveData
    }
}