//package com.example.koin.practice
//
//import android.app.Application
//import com.example.koin.MainActivity
//import org.koin.android.ext.koin.androidContext
//import org.koin.android.ext.koin.androidFileProperties
//import org.koin.android.ext.koin.androidLogger
//import org.koin.core.context.startKoin
//import org.koin.core.logger.Level
//import org.koin.core.module.dsl.viewModel
//import org.koin.core.qualifier.named
//import org.koin.dsl.module
//
//class MyApplication : Application() {
//
//    class ClassA
//    class ClassB(private val classA: ClassA)
//
//    val moduleForClassA = module {
//        // single { ClassA() }
//        single(named("name")) { ClassA() }
//    }
//
//    val moduleForClassB = module {
//        // factory { ClassB(get()) }
//        scope(named<MainActivity>()) {
//            scoped { ClassB(get(named("name"))) }
//        }
//
//        viewModel { MyViewModel(get()) }
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//
//        startKoin {
//            androidLogger(Level.INFO)
//            androidContext(this@MyApplication)
//            androidFileProperties()
//            modules(listOf(moduleForClassA, moduleForClassB))
//        }
//    }
//}