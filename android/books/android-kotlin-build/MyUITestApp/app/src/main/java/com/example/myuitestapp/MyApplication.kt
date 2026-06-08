package com.example.myuitestapp

import android.app.Application
import java.util.Random
import java.util.Timer

open class MyApplication : Application() {

    /*val countingIdlingResource = CountingIdlingResource("Timer resource")
    val randomizer = Randomizer(Random())
    val synchronizer = Synchronizer(randomizer, Timer(), countingIdlingResource)*/

    lateinit var synchronizer: Synchronizer

    override fun onCreate() {
        super.onCreate()
        synchronizer = createSynchronizer()
    }

    open fun createRandomizer(): Randomizer = RandomizerImpl(Random())

    open fun createSynchronizer(): Synchronizer = SynchronizerImpl(createRandomizer(), Timer())
}
