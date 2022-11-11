package com.example.dudeulimproject.view.search_result.repository

import com.example.dudeulimproject.data.remote.MainService
import com.example.dudeulimproject.data.response.InterViewResponse
import com.example.dudeulimproject.data.response.InterViewSeeMoreResponse
import com.example.dudeulimproject.utils.Constants
import retrofit2.Response
import javax.inject.Inject

class SearchResultRepositoryImpl @Inject constructor(private val service: MainService): SearchResultRepository{

    override suspend fun getSearchInterview(
        category: String,
        type: Int,
        amountFrom: Int,
        amountTo: Int,
        title: String?
    ): Response<List<InterViewResponse>> {
        return service.getInterView(Constants.ACCESS_TOKEN.toString(), category, type, amountFrom, amountTo, title, 40, 1)
    }


}