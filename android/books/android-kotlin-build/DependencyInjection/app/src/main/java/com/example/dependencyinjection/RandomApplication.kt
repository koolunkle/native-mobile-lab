package com.example.dependencyinjection

import android.app.Application
import com.example.dependencyinjection.container.ApplicationContainer

class RandomApplication : Application() {

    val applicationContainer = ApplicationContainer()

    override fun onCreate() {
        super.onCreate()
    }
}