package com.example.dudeulimproject.view.create_inter_view.repository

import com.example.dudeulimproject.data.request.InterviewPostRequest
import com.example.dudeulimproject.data.response.InterViewPostResponse
import retrofit2.Response

interface CreateInterViewRepository {
    suspend fun createInterView(request: InterviewPostRequest): Response<InterViewPostResponse>
}