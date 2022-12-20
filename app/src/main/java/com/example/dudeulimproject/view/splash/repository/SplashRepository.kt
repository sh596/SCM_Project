package com.example.dudeulimproject.view.splash.repository

import retrofit2.Response

interface SplashRepository {
    suspend fun checkLogin(): Response<String>
}