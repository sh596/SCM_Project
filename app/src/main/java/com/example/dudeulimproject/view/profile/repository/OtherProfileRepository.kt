package com.example.dudeulimproject.view.profile.repository

import com.example.dudeulimproject.data.response.ProfileResponse
import retrofit2.Response

interface OtherProfileRepository {
    suspend fun getProfile(id: String) : Response<ProfileResponse>
}