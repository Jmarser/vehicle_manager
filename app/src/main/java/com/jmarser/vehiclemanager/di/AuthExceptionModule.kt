package com.jmarser.vehiclemanager.di

import com.jmarser.vehiclemanager.presentation.utils.ExceptionStringMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthExceptionModule {

    @Provides
    @Singleton
    fun provideExceptionStringModule(): ExceptionStringMapper{
        return ExceptionStringMapper()
    }
}