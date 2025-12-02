package com.jmarser.vehiclemanager.di

import com.google.firebase.auth.FirebaseAuth
import com.jmarser.vehiclemanager.data.dataSource.AuthRemoteDataSource
import com.jmarser.vehiclemanager.data.firebase.FirebaseAuthDataSourceImpl
import com.jmarser.vehiclemanager.data.repository.AuthRepositoryImpl
import com.jmarser.vehiclemanager.domain.repository.auth.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthDataModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(auth: FirebaseAuth): AuthRemoteDataSource =
        FirebaseAuthDataSourceImpl(auth)

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: AuthRemoteDataSource): AuthRepository =
        AuthRepositoryImpl(remoteDataSource)
}