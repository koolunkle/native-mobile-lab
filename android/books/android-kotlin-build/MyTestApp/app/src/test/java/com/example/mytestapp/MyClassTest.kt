package com.example.mytestapp

import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.math.BigInteger

@RunWith(Parameterized::class)
class MyClassTest(
    private val input: Int,
    private val expected: BigInteger
) {
    companion object {
        @Parameterized.Parameters
        @JvmStatic
        fun getData(): Collection<Array<Any>> = listOf(
            arrayOf(0, BigInteger.ONE),
            arrayOf(1, BigInteger.ONE),
            arrayOf(2, BigInteger.valueOf(2)),
            arrayOf(3, BigInteger.valueOf(6)),
            arrayOf(4, BigInteger.valueOf(24)),
            arrayOf(5, BigInteger.valueOf(120)),
            arrayOf(13, BigInteger.valueOf(6227020800)),
        )
    }

    @JvmField
    @Rule
    val resultRule = ResultRule()

    private val myClass = MyClass()

    /*@Before
    fun setUp() {
        MyClass.result = BigInteger.ONE
    }*/

    @Test
    fun computeFactorial() {
        /*val n = 3
        val result = myClass.factorial(n)

        assertEquals(6, result)*/

        val result = myClass.factorial(input)
        assertEquals(expected, result)
    }

    /*@After
    fun tearDown() {
        MyClass.result = BigInteger.ONE
    }*/
}