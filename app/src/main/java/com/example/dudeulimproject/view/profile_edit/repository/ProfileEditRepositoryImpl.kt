package com.example.dudeulimproject.view.profile_edit.repository

import com.example.dudeulimproject.data.remote.MainService
import com.example.dudeulimproject.data.request.UpdateProfileRequest
import retrofit2.Response
import javax.inject.Inject

class ProfileEditRepositoryImpl @Inject constructor(private val service: MainService) : ProfileEditRepository {
    override suspend fun updateProfile(request:UpdateProfileRequest): Response<Boolean> {
        return service.updateProfile(request)
    }
}