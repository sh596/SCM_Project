package com.example.dudeulimproject.data.response

import com.example.dudeulimproject.data.User
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestInterViewResponse(
    val category: String,
    val completed: Boolean,
    val date: String?,
    val id: String,
    val location: String?,
    val questions: List<String?>,
    val thumbnail: String,
    val title: String,
)