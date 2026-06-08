package com.example.myuitestapp

import androidx.test.espresso.idling.CountingIdlingResource

class TestSynchronizer(
    private val synchronizer: Synchronizer,
    private val countingIdlingResource: CountingIdlingResource
) : Synchronizer {
    override fun executeAfterDelay(callback: (Int) -> Unit) {
        countingIdlingResource.increment()
        synchronizer.executeAfterDelay { seconds ->
            callback(seconds)
            countingIdlingResource.decrement()
        }
    }
}
