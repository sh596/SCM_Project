package com.example.dudeulimproject.view.splash.repository

import com.example.dudeulimproject.data.remote.MainService
import retrofit2.Response
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(private val service: MainService) : SplashRepository {
    override suspend fun checkLogin(): Response<String> {
        return service.checkLogin()
    }
}