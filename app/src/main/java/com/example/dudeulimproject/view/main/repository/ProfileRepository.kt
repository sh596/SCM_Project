package com.example.dudeulimproject.view.main.repository

import com.example.dudeulimproject.data.response.ProfileResponse
import retrofit2.Response

interface ProfileRepository {
    suspend fun getMyProfile() : Response<ProfileResponse>

}