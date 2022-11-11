package com.example.dudeulimproject.data.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistRequest (
    val token :String,
    val name : String,
    val image: String
    )