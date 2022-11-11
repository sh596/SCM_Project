package com.example.dudeulimproject.data.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestRequest(
    val category: String,
    val date: String,
    val location: String,
    val thumbnail: String,
    val title: String
)