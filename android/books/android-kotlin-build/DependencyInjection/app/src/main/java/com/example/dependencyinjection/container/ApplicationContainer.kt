package com.example.dependencyinjection.container

import com.example.dependencyinjection.repository.NumberRepository
import com.example.dependencyinjection.repository.NumberRepositoryImpl
import java.util.Random

class ApplicationContainer {

    val numberRepository: NumberRepository = NumberRepositoryImpl(Random())
}