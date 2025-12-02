package com.jmarser.vehiclemanager.domain.useCase.auth

import com.jmarser.vehiclemanager.data.model.LoginData
import com.jmarser.vehiclemanager.domain.repository.auth.AuthRepository
import javax.inject.Inject

/**
 * Project: Vehicle manager
 * File: LoginUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/12/2025
 */

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    operator fun invoke(email: String, password: String) = repository.login(
        LoginData(email = email, password = password)
    )
}