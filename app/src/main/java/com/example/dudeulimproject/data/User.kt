package com.example.dudeulimproject.data

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class User(
    val description: String?,
    val email: String,
    val id: String,
    val image: String,
    val job: String?,
    val name: String
)