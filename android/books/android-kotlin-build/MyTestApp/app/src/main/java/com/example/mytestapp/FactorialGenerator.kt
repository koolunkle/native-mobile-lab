package com.example.mytestapp

import java.math.BigInteger

class FactorialGenerator {

    @Throws(FactorialNotFoundException::class)
    fun factorial(n: Int): BigInteger {
        if (n < 0) {
            throw FactorialNotFoundException()
        }

        var result = BigInteger.ONE
        for (i in 1..n) {
            result *= i.toBigInteger()
        }

        return result
    }

    class FactorialNotFoundException : Throwable()
}