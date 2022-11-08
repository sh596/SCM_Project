package com.example.dudeulimproject.data

import com.example.dudeulimproject.UserData


data class RequestInterViewSeeMoreData(
    val id: Int,
    val title: String,
    val date: String,
    val place : String,
    val category: String,
    val user: UserData,
    val question: List<String>
    )
