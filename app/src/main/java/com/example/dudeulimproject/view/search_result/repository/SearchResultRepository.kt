package com.example.dudeulimproject.view.search_result.repository

import com.example.dudeulimproject.data.response.InterViewResponse
import retrofit2.Response

interface SearchResultRepository {
    suspend fun getSearchInterview(
        category: String, type: Int, amountFrom: Int, amountTo: Int,
        title: String?
    ): Response<List<InterViewResponse>>
}