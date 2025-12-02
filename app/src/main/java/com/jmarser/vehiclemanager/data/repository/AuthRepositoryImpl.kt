package com.jmarser.vehiclemanager.data.repository

import com.jmarser.vehiclemanager.data.dataSource.AuthRemoteDataSource
import com.jmarser.vehiclemanager.data.mapper.mapResult
import com.jmarser.vehiclemanager.data.mapper.toDomain
import com.jmarser.vehiclemanager.data.model.AuthCredentialsData
import com.jmarser.vehiclemanager.data.model.LoginData
import com.jmarser.vehiclemanager.domain.model.User
import com.jmarser.vehiclemanager.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Project: Vehicle manager
 * File: AuthRepositoryImpl
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/12/2025
 */

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthRemoteDataSource
) : AuthRepository {
    override fun login(loginData: LoginData): Flow<Result<User>> {
        TODO("Not yet implemented")
    }

    override fun register(authCredentials: AuthCredentialsData): Flow<Result<User>> = authDataSource.register(authCredentials).mapResult { it.toDomain() }
}
