package com.example.dudeulimproject.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileResponse(
    val description: String?,
    val email: String,
    val id: String,
    val image: String,
    val interviews: List<InterViewResponse?>?,
    val job: String?,
    val name: String,
    val requests: List<RequestResponse?>?
)