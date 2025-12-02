package com.jmarser.vehiclemanager.di

import com.jmarser.vehiclemanager.domain.repository.auth.AuthRepository
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
    fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase = RegisterUseCase(repository)
}