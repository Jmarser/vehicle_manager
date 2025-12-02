package com.jmarser.vehiclemanager.domain.repository.auth

import com.jmarser.vehiclemanager.data.model.AuthCredentialsData
import com.jmarser.vehiclemanager.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Project: Vehicle manager
 * File: AuthRepository
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/12/2025
 */
 
interface AuthRepository {

    fun register(authCredentials: AuthCredentialsData): Flow<Result<User>>
}