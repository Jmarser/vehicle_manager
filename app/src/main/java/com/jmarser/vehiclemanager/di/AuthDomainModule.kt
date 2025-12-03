package com.jmarser.vehiclemanager.di

import com.jmarser.vehiclemanager.domain.repository.auth.AuthRepository
import com.jmarser.vehiclemanager.domain.useCase.auth.ForgotPasswordUseCase
import com.jmarser.vehiclemanager.domain.useCase.auth.GetCurrentUseCase
import com.jmarser.vehiclemanager.domain.useCase.auth.LoginUseCase
import com.jmarser.vehiclemanager.domain.useCase.auth.LogoutUseCase
import com.jmarser.vehiclemanager.domain.useCase.auth.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthDomainModule {
    @Provides
    @Singleton
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase = LoginUseCase(repository)

    @Provides
    @Singleton
    fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase = RegisterUseCase(repository)

    @Provides
    @Singleton
    fun provideForgotPasswordUseCase(repository: AuthRepository): ForgotPasswordUseCase = ForgotPasswordUseCase(repository)

    @Provides
    @Singleton
    fun provideGetCurrentUserUseCase(repository: AuthRepository): GetCurrentUseCase = GetCurrentUseCase(repository)

    @Provides
    @Singleton
    fun provideLogoutUseCase(repository: AuthRepository): LogoutUseCase = LogoutUseCase(repository)
}
