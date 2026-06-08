package com.example.myrecyclerviewapp.model

import java.util.concurrent.atomic.AtomicLong

data class CatUiModel(
    val id: Long = nextId(),
    val gender: Gender,
    val breed: CatBreed,
    val name: String,
    val biography: String,
    val imageUrl: String
) {
    companion object {
        private val counter = AtomicLong(1)

        fun nextId(): Long = counter.getAndIncrement()
    }
}
