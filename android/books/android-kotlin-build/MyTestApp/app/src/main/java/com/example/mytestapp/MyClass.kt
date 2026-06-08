package com.example.mytestapp

import java.math.BigInteger

class MyClass {
    // fun factorial(n: Int): Int = IntArray(n) { it + 1 }.reduce { acc, i -> acc * i }
    // fun factorial(n: Int): Int = IntArray(n) { it + 1 }.fold(1) { acc, i -> acc * i }

    @Throws(FactorialNotFoundException::class)
    fun factorial(n: Int): BigInteger {
        if (n < 0) {
            throw FactorialNotFoundException()
        }/* return IntArray(n) { it + 1 }.fold(BigInteger.ONE) { acc, i ->
             acc * i.toBigInteger()
         }*/

        // var result = BigInteger.ONE
        for (i in 1..n) {
            // result *= i.toBigInteger()
            result = result.times(i.toBigInteger())
        }
        return result
    }

    class FactorialNotFoundException : Throwable()

    companion object {
        var result : BigInteger = BigInteger.ONE
    }
}