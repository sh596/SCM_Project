package com.example.dudeulimproject.data.response

import com.example.dudeulimproject.data.User
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class InterViewPostResponse(
    val amount: Int,
    val category: String,
    val description: String,
    val field: Int,
    val id: String,
    val thumbnail: String,
    val title: String,
    val user: User
)