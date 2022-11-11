package com.example.dudeulimproject.view.main.repository

import com.example.dudeulimproject.data.response.InterViewResponse
import com.example.dudeulimproject.data.response.InterViewSeeMoreResponse
import retrofit2.Response

interface MainFragmentRepository {
    suspend fun getExploreInterViewList() : Response<List<InterViewResponse>>
}