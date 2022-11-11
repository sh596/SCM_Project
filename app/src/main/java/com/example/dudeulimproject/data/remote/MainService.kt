package com.example.dudeulimproject.data.remote

import com.example.dudeulimproject.data.request.LoginRequest
import com.example.dudeulimproject.data.request.RegistRequest
import com.example.dudeulimproject.data.request.RequestRequest
import com.example.dudeulimproject.data.response.*
import retrofit2.Response
import retrofit2.http.*

interface MainService {
    @POST("/auth/login")
    suspend fun login(@Body token: LoginRequest): Response<TokenResponse>

    @POST("/auth/regist")
    suspend fun regist(@Body request: RegistRequest): Response<TokenResponse>

    @POST("/interview")
    suspend fun postInterView(@Body interViewSeeMoreResponse: InterViewSeeMoreResponse): Response<InterViewSeeMoreResponse>

    @GET("/interview")
    suspend fun getInterView(
        @Header("Authorization") token: String,
        @Query("category") category: String,
        @Query("type") type: Int,
        @Query("amountFrom") amountFrom: Int,
        @Query("amountTo") amountTo: Int?,
        @Query("title") title: String? = null,
        @Query("limit") limit: Int?,
        @Query("page") page: Int?,
    ): Response<List<InterViewResponse>>

    @GET("/interview")
    suspend fun getInterViewAll(
        @Header("Authorization") token: String,
    ): Response<List<InterViewResponse>>


    @GET("/interview/{id}")
    suspend fun getInterViewById(
        @Header("Authorization") token: String,
        @Path("id") interViewId: String
    ): Response<InterViewSeeMoreResponse>

    @POST("/request/{int_id}")
    suspend fun requestInterView(@Header("Authorization") token: String, @Path("int_id") id: String,
                                 @Body request: RequestRequest
    ): Response<RequestResponse>

    @GET("/profile/requests/incoming")
    suspend fun getRequestInterView(@Header("Authorization") token: String): Response<List<RequestInterViewResponse>>

    @GET("/profile/me")
    suspend fun getMyProfile(@Header("Authorization") token: String): Response<ProfileResponse>


}