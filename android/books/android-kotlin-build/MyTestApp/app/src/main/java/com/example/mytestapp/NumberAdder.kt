package com.example.mytestapp

import java.math.BigInteger

class NumberAdder {

    @Throws(InvalidNumberException::class)
    fun sum(n: Int, callback: (BigInteger) -> Unit) {
        if (n < 0) {
            throw InvalidNumberException()
        }

        /*var result = BigInteger.ZERO
        for (i in 1..n) {
            result += i.toBigInteger()
        }*/

        val result =
            n.toBigInteger()
                .times(n.toBigInteger().plus(1.toBigInteger()))
                .divide(2.toBigInteger())

        callback(result)
    }

    class InvalidNumberException : Throwable()
}