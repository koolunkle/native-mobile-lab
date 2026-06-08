package com.example.sharedpreferences

import android.app.Application

class PreferenceApplication : Application() {

    lateinit var preferenceWrapper: PreferenceWrapper

    override fun onCreate() {
        super.onCreate()
        preferenceWrapper = PreferenceWrapper(getSharedPreferences("prefs", MODE_PRIVATE))
    }
}