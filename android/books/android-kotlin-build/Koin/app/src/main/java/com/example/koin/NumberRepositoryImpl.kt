package com.example.koin

import java.util.Random

class NumberRepositoryImpl(private val random: Random) : NumberRepository {

    override fun generateNextNumber(): Int {
        return random.nextInt()
    }
}