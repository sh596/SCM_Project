package com.example.dudeulimproject.data


data class RequestInterViewSeeMoreData(
    val id: String,
    val title: String,
    val date: String,
    val place: String,
    val category: String,
    val record: List<String>?,
    val publisher: User,
    val requester: User
    )
