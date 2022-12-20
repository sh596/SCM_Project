package com.example.dudeulimproject.data

data class RequestInterViewData(
    val id: String,
    val thumbnail: String,
    val date: String?,
    val title: String,
    val category: String,
    val isRequester: Boolean,
    val user: User,
    )
