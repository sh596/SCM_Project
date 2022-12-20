package com.example.dudeulimproject.data.request

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class InterviewPostRequest(
    val amount: Int,
    val category: String,
    val description: String,
    val field: Int,
    val thumbnail: String,
    val title: String
)