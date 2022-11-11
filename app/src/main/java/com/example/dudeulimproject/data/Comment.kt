package com.example.dudeulimproject.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Comment (
    val id : String,
    val score : Int,
    val content : String,
    val user: User
        )