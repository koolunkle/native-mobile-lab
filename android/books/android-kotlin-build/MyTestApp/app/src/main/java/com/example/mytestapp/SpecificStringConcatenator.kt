package com.example.mytestapp

class SpecificStringConcatenator(private val stringConcatenator: StringConcatenator) {

    fun concatenateSpecificStrings(): String {
        return stringConcatenator.concatenate(
            R.string.string_1, R.string.string_2
        )
    }

    fun concatenateWithCallback(callback: Callback) {
        callback.onStringReady(concatenateSpecificStrings())
    }

    interface Callback {
        fun onStringReady(input: String)
    }
}