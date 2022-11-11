package com.example.dudeulimproject.data.response

import com.example.dudeulimproject.data.User
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestResponse(
    val category: String,
    val completed: Boolean,
    val date: String?,
    val deletedAt: String?,
    val id: String,
    val interview: Interview?,
    val location: String?,
    val thumbnail: String,
    val title: String,
)
