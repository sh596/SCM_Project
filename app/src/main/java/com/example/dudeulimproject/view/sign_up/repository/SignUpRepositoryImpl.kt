package com.example.dudeulimproject.view.sign_up.repository

import com.example.dudeulimproject.data.remote.MainService
import com.example.dudeulimproject.data.request.RegistRequest
import com.example.dudeulimproject.data.response.TokenResponse
import retrofit2.Response
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(private val service: MainService) : SignUpRepository {
    override suspend fun singUp(
        request: RegistRequest
    ): Response<TokenResponse> {
        return service.regist(request)
    }

}