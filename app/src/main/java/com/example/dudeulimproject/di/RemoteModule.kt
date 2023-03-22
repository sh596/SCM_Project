//package com.example.dudeulimproject.di
//
//import android.content.Context
//import android.content.SharedPreferences
//import com.example.dudeulimproject.data.remote.MainService
//import com.example.dudeulimproject.view.chat.repository.ChatRepository
//import com.example.dudeulimproject.view.chat.repository.ChatRepositoryImpl
//import com.google.firebase.storage.FirebaseStorage
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import okhttp3.Interceptor
//import okhttp3.OkHttpClient
//import okhttp3.Response
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory
//import java.util.*
//import java.util.concurrent.TimeUnit
//import javax.inject.Singleton
//
//
//@Module
//@InstallIn(SingletonComponent::class)
//object RemoteModule {
//
//    @Singleton
//    @Provides
//    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }
//    @Singleton
//    @Provides
//    fun headerInterceptor(preferences: SharedPreferences) = Interceptor { chain ->
//        val token = preferences.getString("accessToken","");
//        val newRequest = chain.request().newBuilder()
//            .addHeader("Authorization", "Bearer $token")
//            .build()
//        chain.proceed(newRequest)
//
//    }
//
//    @Singleton
//    @Provides
//    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, headerInterceptor: Interceptor): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(headerInterceptor)
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideMoshi(): Moshi = Moshi.Builder().build()
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
//        .client(okHttpClient)
//        .addConverterFactory(ScalarsConverterFactory.create())
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .baseUrl("http://plebea.com:2200/")
//        .build()
//
//    @Singleton
//    @Provides
//    fun provideMainService(retrofit: Retrofit): MainService =
//        retrofit.create(MainService::class.java)
//
//    @Singleton
//    @Provides
//    fun provideChatRepository(
//    ): ChatRepository
//            = ChatRepositoryImpl()
//}