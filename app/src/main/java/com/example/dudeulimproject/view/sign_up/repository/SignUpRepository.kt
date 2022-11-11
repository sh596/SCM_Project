package com.example.dudeulimproject.view.sign_up.repository

import com.example.dudeulimproject.data.request.RegistRequest
import com.example.dudeulimproject.data.response.TokenResponse
import retrofit2.Response

interface SignUpRepository {
    suspend fun singUp(request: RegistRequest) : Response<TokenResponse>
}