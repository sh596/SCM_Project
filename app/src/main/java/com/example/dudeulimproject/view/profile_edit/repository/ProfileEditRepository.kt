package com.example.dudeulimproject.view.profile_edit.repository

import com.example.dudeulimproject.data.request.UpdateProfileRequest
import retrofit2.Response

interface ProfileEditRepository {
    suspend fun updateProfile(request: UpdateProfileRequest) : Response<Boolean>
}