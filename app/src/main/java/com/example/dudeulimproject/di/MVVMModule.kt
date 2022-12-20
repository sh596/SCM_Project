package com.example.dudeulimproject.di

import com.example.dudeulimproject.data.remote.MainService
import com.example.dudeulimproject.view.chat.repository.ChatRepository
import com.example.dudeulimproject.view.chat.repository.ChatRepositoryImpl
import com.example.dudeulimproject.view.create_inter_view.repository.CreateInterViewRepository
import com.example.dudeulimproject.view.create_inter_view.repository.CreateInterViewRepositoryImpl
import com.example.dudeulimproject.view.info_modify.repository.InfoModifyRepository
import com.example.dudeulimproject.view.info_modify.repository.InfoModifyRepositoryImpl
import com.example.dudeulimproject.view.main.repository.ProfileRepository
import com.example.dudeulimproject.view.main.repository.ProfileRepositoryImpl
import com.example.dudeulimproject.view.proceed_inter_view.repository.ProceedInterViewRepository
import com.example.dudeulimproject.view.proceed_inter_view.repository.ProceedInterViewRepositoryImpl
import com.example.dudeulimproject.view.profile.repository.OtherProfileRepository
import com.example.dudeulimproject.view.profile.repository.OtherProfileRepositoryImpl
import com.example.dudeulimproject.view.profile_edit.repository.ProfileEditRepository
import com.example.dudeulimproject.view.profile_edit.repository.ProfileEditRepositoryImpl
import com.example.dudeulimproject.view.request_inter_view.repository.RequestInterViewRepository
import com.example.dudeulimproject.view.request_inter_view.repository.RequestInterViewRepositoryImpl
import com.example.dudeulimproject.view.sign_up.repository.SignUpRepository
import com.example.dudeulimproject.view.sign_up.repository.SignUpRepositoryImpl
import com.example.dudeulimproject.view.splash.repository.SplashRepository
import com.example.dudeulimproject.view.splash.repository.SplashRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MVVMModule {


//    @Singleton
//    @Provides
//    fun provideSignUpRepository(
//        service: MainService
//    ): SignUpRepository = SignUpRepositoryImpl(service)

    @Binds
    fun provideCreateInterViewRepository(repository: CreateInterViewRepositoryImpl): CreateInterViewRepository

    @Binds
    fun provideSignUpRepository(repository: SignUpRepositoryImpl): SignUpRepository

    @Binds
    fun provideRequestInterViewRepository(repository: RequestInterViewRepositoryImpl) : RequestInterViewRepository

    @Binds
    fun provideSplashRepository(repository: SplashRepositoryImpl) : SplashRepository

    @Binds
    fun providesProfileEditRepository(repository: ProfileEditRepositoryImpl) : ProfileEditRepository

    @Binds
    fun providesProfileRepository(repository: ProfileRepositoryImpl): ProfileRepository

    @Binds
    fun providesInfoModifyRepository(repository: InfoModifyRepositoryImpl): InfoModifyRepository

    @Binds
    fun providesOtherProfileRepository(repository: OtherProfileRepositoryImpl): OtherProfileRepository

    @Binds
    fun providesProceedInterViewRepository(repository: ProceedInterViewRepositoryImpl) : ProceedInterViewRepository
}