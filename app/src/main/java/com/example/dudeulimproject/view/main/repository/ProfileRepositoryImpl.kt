package com.example.dudeulimproject.view.main.repository

import com.example.dudeulimproject.data.remote.MainService
import com.example.dudeulimproject.data.response.ProfileResponse
import com.example.dudeulimproject.utils.Constants
import retrofit2.Response
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val service: MainService) : ProfileRepository{
    override suspend fun getMyProfile() : Response<ProfileResponse>{
        return service.getMyProfile()
    }
}