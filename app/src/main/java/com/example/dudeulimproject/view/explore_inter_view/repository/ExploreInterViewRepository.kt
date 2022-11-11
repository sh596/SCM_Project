package com.example.dudeulimproject.view.explore_inter_view.repository

import com.example.dudeulimproject.data.request.RequestRequest
import com.example.dudeulimproject.data.response.InterViewSeeMoreResponse
import com.example.dudeulimproject.data.response.RequestResponse
import retrofit2.Response

interface ExploreInterViewRepository {
    suspend fun getExploreInterView(id: String) : Response<InterViewSeeMoreResponse>
    suspend fun requestInterView(id: String, request: RequestRequest) : Response<RequestResponse>
}