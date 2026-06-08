package com.example.myuitestapp

class Adder {
    fun sum(n: Int): Long {
        /*var result = 0L
        for (i in 1..n) {
            result += i
        }
        return result*/

        if (n <= 0) return -1

        return (n * (n.toLong() + 1)) / 2
    }
}