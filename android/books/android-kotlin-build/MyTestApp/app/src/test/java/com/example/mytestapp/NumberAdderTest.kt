package com.example.mytestapp

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import java.math.BigInteger

@RunWith(Parameterized::class)
class NumberAdderTest(
    private val input: Int,
    private val expected: BigInteger
) {
    companion object {
        @Parameterized.Parameters
        @JvmStatic
        fun getData(): List<Array<out Any>> = listOf(
            arrayOf(0, BigInteger.ZERO),
            arrayOf(1, BigInteger.ONE),
            arrayOf<Any>(5, 15.toBigInteger()),
            arrayOf<Any>(20, 210.toBigInteger()),
            arrayOf<Any>(Int.MAX_VALUE, BigInteger("2305843008139952128")),
        )
    }

    private val numberAdder = NumberAdder()

    @Test
    fun sum() {
        val callback = mock<(BigInteger) -> Unit>()
        numberAdder.sum(input, callback)
        verify(callback).invoke(expected)
    }
}