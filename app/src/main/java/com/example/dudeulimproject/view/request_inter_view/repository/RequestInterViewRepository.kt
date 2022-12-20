package com.example.dudeulimproject.view.request_inter_view.repository

import com.example.dudeulimproject.data.response.RequestInterViewResponse
import retrofit2.Response

interface RequestInterViewRepository {
    suspend fun getRequestInterView(int_id: String, id: String): Response<List<RequestInterViewResponse>>
}
