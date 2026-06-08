package com.example.catagentprofile.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageResultData(
    @param:Json(name = "url") val imageUrl: String,
    @param:Json(name = "breeds") val breeds: List<CatBreedData>?
)