package com.example.myuitestapp

class TestRandomizer : Randomizer {
    override fun getTimeToWait(): Int {
        return 1
    }
}