package com.example.dudeulimproject.data.remote

import com.example.dudeulimproject.data.request.*
import com.example.dudeulimproject.data.response.*
import retrofit2.Response
import retrofit2.http.*

interface MainService {
    @POST("/auth/login")
    suspend fun login(@Body token: LoginRequest): Response<TokenResponse>

    @POST("/auth/regist")
    suspend fun regist(@Body request: RegistRequest): Response<TokenResponse>

    @POST("/interview")
    suspend fun postInterView(@Body request: InterviewPostRequest): Response<InterViewPostResponse>

    @GET("/interview")
    suspend fun getInterView(
        @Query("category") category: String?,
        @Query("type") type: Int?,
        @Query("amountFrom") amountFrom: Int?,
        @Query("amountTo") amountTo: Int?,
        @Query("title") title: String? = null,
        @Query("limit") limit: Int?,
        @Query("page") page: Int?,
    ): Response<List<InterViewResponse>>

    @GET("/interview")
    suspend fun getInterViewAll(
    ): Response<List<InterViewResponse>>


    @GET("/interview/{id}")
    suspend fun getInterViewById(
        @Path("id") interViewId: String
    ): Response<InterViewSeeMoreResponse>

    @POST("/request/{int_id}")
    suspend fun requestInterView(@Path("int_id") id: String,
                                 @Body request: RequestRequest
    ): Response<RequestResponse>

    @GET("/profile/requests/incoming")
    suspend fun getRequestInterView(): Response<List<RequestInterViewResponse>>

    @GET("/profile/me")
    suspend fun getMyProfile(): Response<ProfileResponse>

    @GET("/auth")
    suspend fun checkLogin() : Response<String>

    @PUT("/profile/me")
    suspend fun updateProfile(@Body request: UpdateProfileRequest) : Response<Boolean>

    @GET("profile/{id}")
    suspend fun getProfile(@Path("id") id: String): Response<ProfileResponse>
}