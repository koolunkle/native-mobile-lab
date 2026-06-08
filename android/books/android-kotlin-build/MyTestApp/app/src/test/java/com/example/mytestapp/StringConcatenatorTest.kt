package com.example.mytestapp

import android.content.Context
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class StringConcatenatorTest {

    // private val context = Mockito.mock(Context::class.java)
    private val context = mock<Context>()
    private val stringConcatenator = StringConcatenator(context)

    @Test
    fun concatenate() {
        val stringRes1 = 1
        val stringRes2 = 2
        val string1 = "string1"
        val string2 = "string2"

        /*Mockito.`when`(context.getString(stringRes1))
            .thenReturn(string1)*/

        /*Mockito.`when`(context.getString(stringRes2))
            .thenReturn(string2)*/

        whenever(context.getString(stringRes1)).thenReturn(string1)
        whenever(context.getString(stringRes2)).thenReturn(string2)

        val result = stringConcatenator.concatenate(stringRes1, stringRes2)

        assertEquals(string1.plus(string2), result)
    }
}