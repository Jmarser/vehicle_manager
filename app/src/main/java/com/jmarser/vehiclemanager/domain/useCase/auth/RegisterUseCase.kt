package com.jmarser.vehiclemanager.domain.useCase.auth

import com.jmarser.vehiclemanager.data.model.AuthCredentialsData
import com.jmarser.vehiclemanager.domain.model.User
import com.jmarser.vehiclemanager.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Project: Vehicle manager
 * File: RegisterUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/12/2025
 */

class RegisterUseCase
    @Inject
    constructor(
        private val repository: AuthRepository,
    ) {
        operator fun invoke(
            name: String,
            email: String,
            password: String,
        ): Flow<Result<User>> =
            repository.register(
                AuthCredentialsData(
                    email = email,
                    password = password,
                    name = name,
                ),
            )
    }
