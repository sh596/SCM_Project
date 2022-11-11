package com.example.dudeulimproject.view.main.repository

import com.example.dudeulimproject.data.remote.MainService
import com.example.dudeulimproject.data.response.InterViewResponse
import com.example.dudeulimproject.data.response.InterViewSeeMoreResponse
import com.example.dudeulimproject.utils.Constants
import retrofit2.Response
import javax.inject.Inject

class MainFragmentRepositoryImpl @Inject constructor(private val service: MainService) :
    MainFragmentRepository {
    override suspend fun getExploreInterViewList(): Response<List<InterViewResponse>> {
        return service.getInterViewAll(Constants.ACCESS_TOKEN.toString())
    }
}