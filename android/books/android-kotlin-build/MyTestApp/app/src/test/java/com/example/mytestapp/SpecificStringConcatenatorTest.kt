package com.example.mytestapp

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SpecificStringConcatenatorTest {


    /*private val stringConcatenator = Mockito.mock(StringConcatenator::class.java)
    private val specificStringConcatenator = SpecificStringConcatenator(stringConcatenator)*/

    @Mock
    lateinit var stringConcatenator: StringConcatenator

    @InjectMocks
    lateinit var specificStringConcatenator: SpecificStringConcatenator

    @Test
    fun concatenateSpecificStrings() {
        val expected = "expected"

        /*Mockito.`when`(stringConcatenator.concatenate(R.string.string_1, R.string.string_2))
            .thenReturn(expected)*/

        whenever(stringConcatenator.concatenate(R.string.string_1, R.string.string_2))
            .thenReturn(expected)

        val result = specificStringConcatenator.concatenateSpecificStrings()

        assertEquals(expected, result)
    }

    @Test
    fun concatenateWithCallback() {
        val expected = "expected"

        // val spy = Mockito.spy(specificStringConcatenator)
        val spy = spy(specificStringConcatenator)

        /*Mockito.`when`(spy.concatenateSpecificStrings())
            .thenReturn(expected)*/

        // Mockito.doReturn(expected).`when`(spy).concatenateSpecificStrings()
        doReturn(expected).whenever(spy).concatenateSpecificStrings()

        // val callback = Mockito.mock(SpecificStringConcatenator.Callback::class.java)
        val callback = mock<SpecificStringConcatenator.Callback>()

        spy.concatenateWithCallback(callback)

        // Mockito.verify(callback).onStringReady(expected)
        verify(callback).onStringReady(expected)
    }
}