package com.example.hilt

import java.util.Random

class NumberRepositoryImpl(private val random: Random) : NumberRepository {

    override fun generateNextNumber(): Int {
        return random.nextInt()
    }
}