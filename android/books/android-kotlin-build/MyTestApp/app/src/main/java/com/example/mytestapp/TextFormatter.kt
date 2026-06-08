package com.example.mytestapp

import android.content.Context

class TextFormatter(
    private val factorialGenerator: FactorialGenerator,
    private val context: Context
) {
    fun getFactorialResult(n: Int): String {
        return try {
            context.getString(R.string.result, factorialGenerator.factorial(n).toString())
        } catch (e: FactorialGenerator.FactorialNotFoundException) {
            context.getString(R.string.error)
        }
    }
}