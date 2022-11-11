package com.example.dudeulimproject.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Interview(
    val id: String
)