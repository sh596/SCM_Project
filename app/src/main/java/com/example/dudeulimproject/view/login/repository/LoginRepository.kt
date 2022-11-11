package com.example.dudeulimproject.view.login.repository

import com.example.dudeulimproject.data.request.LoginRequest
import com.example.dudeulimproject.data.response.TokenResponse
import retrofit2.Response

interface LoginRepository {
    suspend fun login(request: LoginRequest) : Response<TokenResponse>
}