package com.example.dudeulimproject.view.profile.repository

import com.example.dudeulimproject.data.remote.MainService
import com.example.dudeulimproject.data.response.ProfileResponse
import retrofit2.Response
import javax.inject.Inject

class OtherProfileRepositoryImpl @Inject constructor(private val service: MainService) : OtherProfileRepository{
    override suspend fun getProfile(id :String): Response<ProfileResponse> {
        return service.getProfile(id)
    }

}