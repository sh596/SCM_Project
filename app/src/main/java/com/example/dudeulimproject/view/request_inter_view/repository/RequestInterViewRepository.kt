package com.example.dudeulimproject.view.request_inter_view.repository

import com.example.dudeulimproject.data.response.RequestInterViewResponse

interface RequestInterViewRepository {
    suspend fun getRequestInterView(int_id: String, id: String): RequestInterViewResponse
}
