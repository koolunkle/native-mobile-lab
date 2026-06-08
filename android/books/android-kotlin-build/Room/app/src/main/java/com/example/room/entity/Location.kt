package com.example.room.entity

import androidx.room.ColumnInfo

data class Location(
    @ColumnInfo(name = "lat") val lat: Double,
    @ColumnInfo(name = "long") val long: Double,
    @ColumnInfo(name = "location_name") val name: String,
)
