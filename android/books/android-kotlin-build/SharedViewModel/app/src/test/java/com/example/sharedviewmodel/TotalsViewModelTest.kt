package com.example.sharedviewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TotalsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    // private val totalsViewModel = TotalsViewModel()
    private lateinit var totalsViewModel: TotalsViewModel

    @Before
    fun setUp() {
        totalsViewModel = TotalsViewModel()
        // assertEquals(0, totalsViewModel.total.value)
        assertEquals(0, totalsViewModel.total.blockingFirst())
    }

    @Test
    fun increaseTotal() {
        val testObserver = totalsViewModel.total.test()
        val total = 5

        for (i in 0 until total) {
            totalsViewModel.increaseTotal()
        }

        testObserver.assertValues(0, 1, 2, 3, 4, 5)

        // assertEquals(5, totalsViewModel.total.value)
        assertEquals(5, testObserver.values().last())
    }
}