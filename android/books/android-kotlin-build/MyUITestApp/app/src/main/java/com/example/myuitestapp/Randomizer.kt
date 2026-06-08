package com.example.myuitestapp

import java.util.Random

interface Randomizer {
    fun getTimeToWait(): Int
}

class RandomizerImpl(private val random: Random) : Randomizer {

    override fun getTimeToWait(): Int {
        return random.nextInt(5) + 1
    }
}