//package com.example.hilt
//
//import dagger.hilt.android.testing.HiltAndroidRule
//import dagger.hilt.android.testing.HiltAndroidTest
//import org.junit.Before
//import org.junit.Rule
//import javax.inject.Inject
//
//@HiltAndroidTest
//class MyInstrumentedTest {
//
//    @get:Rule
//    var hiltRule = HiltAndroidRule(this)
//
//    @Inject
//    lateinit var myObject: MyObject
//
//    @Before
//    fun init() {
//        hiltRule.inject()
//    }
//}