package com.example.catagentprofile.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatBreedData(
    @param:Json(name = "name") val name: String,
    @param:Json(name = "temperament") val temperament: String?
)