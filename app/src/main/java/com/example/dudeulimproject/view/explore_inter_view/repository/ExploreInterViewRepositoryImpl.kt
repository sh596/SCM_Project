package com.example.dudeulimproject.view.explore_inter_view.repository

import com.example.dudeulimproject.data.remote.MainService
import com.example.dudeulimproject.data.request.RegistRequest
import com.example.dudeulimproject.data.request.RequestRequest
import com.example.dudeulimproject.data.response.InterViewSeeMoreResponse
import com.example.dudeulimproject.data.response.RequestResponse
import com.example.dudeulimproject.utils.Constants
import retrofit2.Response
import javax.inject.Inject

class ExploreInterViewRepositoryImpl @Inject constructor(private val service: MainService) : ExploreInterViewRepository {
    override suspend fun getExploreInterView(id: String): Response<InterViewSeeMoreResponse> {
        return service.getInterViewById(id)
    }
    override suspend fun requestInterView(id: String, request: RequestRequest): Response<RequestResponse> {
        return service.requestInterView(id,request)
    }
}