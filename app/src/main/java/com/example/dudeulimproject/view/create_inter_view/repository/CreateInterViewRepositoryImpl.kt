package com.example.dudeulimproject.view.create_inter_view.repository

import com.example.dudeulimproject.data.remote.MainService
import com.example.dudeulimproject.data.request.InterviewPostRequest
import com.example.dudeulimproject.data.response.InterViewPostResponse
import retrofit2.Response
import javax.inject.Inject

class CreateInterViewRepositoryImpl @Inject constructor(private val service: MainService) : CreateInterViewRepository {
    override suspend fun createInterView(request: InterviewPostRequest): Response<InterViewPostResponse> {
        return service.postInterView(request)
    }
}