//package com.example.datastore
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.core.booleanPreferencesKey
//import androidx.datastore.preferences.core.edit
//import androidx.datastore.preferences.core.intPreferencesKey
//import androidx.datastore.preferences.core.stringPreferencesKey
//import androidx.datastore.preferences.preferencesDataStore
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//
//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "myDataStore")
//
//val KEY_MY_INT = intPreferencesKey("my_int_key")
//val KEY_MY_BOOLEAN = booleanPreferencesKey("my_boolean_key")
//val KEY_MY_STRING = stringPreferencesKey("my_string_key")
//
//class MyAppSettings(private val context: Context) {
//
//    val myIntValue: Flow<Int> = context.dataStore.data.map { preferences ->
//        preferences[KEY_MY_INT] ?: 0
//    }
//
//    val myBooleanValue: Flow<Boolean> = context.dataStore.data.map { preferences ->
//        preferences[KEY_MY_BOOLEAN] ?: false
//    }
//
//    val myStringValue: Flow<String> = context.dataStore.data.map { preferences ->
//        preferences[KEY_MY_STRING] ?: ""
//    }
//
//    suspend fun saveMyIntValue(intValue: Int) {
//        context.dataStore.edit { preferences ->
//            preferences[KEY_MY_INT] = intValue
//        }
//    }
//
//    suspend fun saveMyBooleanValue(booleanValue: Boolean) {
//        context.dataStore.edit { preferences ->
//            preferences[KEY_MY_BOOLEAN] = booleanValue
//        }
//    }
//
//    suspend fun saveMyStringValue(stringValue: String) {
//        context.dataStore.edit { preferences ->
//            preferences[KEY_MY_STRING] = stringValue
//        }
//    }
//}