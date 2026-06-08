package com.example.mytestapp

import android.content.Context
import androidx.annotation.StringRes

class StringConcatenator(private val context: Context) {

    fun concatenate(@StringRes stringRes1: Int, @StringRes stringRes2: Int): String {
        return context.getString(stringRes1).plus(context.getString(stringRes2))
    }
}