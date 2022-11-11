package com.example.dudeulimproject.view.main.repository

import com.example.dudeulimproject.data.remote.MainService
import com.example.dudeulimproject.data.response.RequestInterViewResponse
import com.example.dudeulimproject.utils.Constants
import retrofit2.Response
import javax.inject.Inject

class RequestFragmentRepositoryImpl @Inject constructor(private val service: MainService) :
    RequestFragmentRepository {
    override suspend fun getRequestInterView(): Response<List<RequestInterViewResponse>> {
        return service.getRequestInterView(Constants.ACCESS_TOKEN.toString())
    }
}