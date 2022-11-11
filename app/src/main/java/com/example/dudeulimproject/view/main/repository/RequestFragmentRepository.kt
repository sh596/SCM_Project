package com.example.dudeulimproject.view.main.repository

import com.example.dudeulimproject.data.response.RequestInterViewResponse
import retrofit2.Response

interface RequestFragmentRepository {
    suspend fun getRequestInterView() : Response<List<RequestInterViewResponse>>
}