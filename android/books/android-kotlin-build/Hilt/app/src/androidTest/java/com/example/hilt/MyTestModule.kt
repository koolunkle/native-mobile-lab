//package com.example.hilt
//
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.components.SingletonComponent
//import dagger.hilt.testing.TestInstallIn
//
//@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [MyModule::class]
//)
//class MyTestModule {
//    @Provides
//    fun provideMyObject(): MyObject = MyTestObject()
//}