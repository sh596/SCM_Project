package com.example.dudeulimproject.data.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateProfileRequest(
    val description: String,
    val image: String,
    val job: String,
    val name: String
)