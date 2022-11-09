package com.example.dudeulimproject.di

import com.example.dudeulimproject.view.chat.repository.ChatRepository
import com.example.dudeulimproject.view.chat.repository.ChatRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MVVMModule {
    @Singleton
    @Provides
    fun provideChatRepository(
    ) : ChatRepository = ChatRepositoryImpl()

}