package com.example.mytestapp

import org.junit.Test

class MyClassTest2 {

    private val myClass = MyClass()

    @Test(expected = MyClass.FactorialNotFoundException::class)
    fun computeNegatives() {
        myClass.factorial(-10)
    }
}