package com.example.dudeulimproject.data


data class RequestInterViewSeeMoreData(
    val id: Int,
    val title: String,
    val date: String,
    val place : String,
    val category: String,
    val user: User,
    val question: List<String>
    )
