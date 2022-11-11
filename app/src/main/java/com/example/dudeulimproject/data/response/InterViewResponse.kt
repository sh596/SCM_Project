package com.example.dudeulimproject.data.response

import com.example.dudeulimproject.data.Comment
import com.example.dudeulimproject.data.User
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class InterViewResponse (
    val amount: Int,
    val category: String,
    val description: String,
    val `field`: Int,
    val id: String,
    val thumbnail: String,
    val title: String,
    val user: User,
    )