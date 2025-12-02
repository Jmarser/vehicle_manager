package com.jmarser.vehiclemanager.data.dataSource

import com.jmarser.vehiclemanager.data.model.AuthCredentialsData
import com.jmarser.vehiclemanager.data.model.LoginData
import com.jmarser.vehiclemanager.data.model.UserData
import kotlinx.coroutines.flow.Flow

/**
 * Project: Vehicle manager
 * File: AuthRemoteDataSource
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/12/2025
 */

interface AuthRemoteDataSource {

    fun login(loginData: LoginData): Flow<Result<UserData>>

    fun register( authCredentials: AuthCredentialsData): Flow<Result<UserData>>
}