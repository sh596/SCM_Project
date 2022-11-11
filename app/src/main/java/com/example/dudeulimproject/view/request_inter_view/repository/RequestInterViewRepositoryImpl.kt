package com.example.dudeulimproject.view.request_inter_view.repository

import com.example.dudeulimproject.data.remote.MainService
import com.example.dudeulimproject.data.response.RequestInterViewResponse
import javax.inject.Inject

class RequestInterViewRepositoryImpl @Inject constructor(private val service: MainService) : RequestInterViewRepository{
    override suspend fun getRequestInterView(int_id: String, id: String): RequestInterViewResponse {
        TODO()
    }
}