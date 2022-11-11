package com.example.dudeulimproject.data

data class InterViewData (
    val id : Int,
    val thumbnail : String,
    val user: User,
    val title: String,
    val introduce: String,
    val category: String,
    val field: String,
    val coin: String,
    val review: List<Review>
    )

data class Review(
    val id: Int,
    val user: User,
    val star: Int,
    val content: String,
)