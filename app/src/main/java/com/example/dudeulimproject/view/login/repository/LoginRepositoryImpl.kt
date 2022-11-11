package com.example.dudeulimproject.view.login.repository

import com.example.dudeulimproject.data.remote.MainService
import com.example.dudeulimproject.data.request.LoginRequest
import com.example.dudeulimproject.data.response.TokenResponse
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val service : MainService) : LoginRepository {
    override suspend fun login(request:LoginRequest) : Response<TokenResponse> {
        return service.login(request)
    }

}