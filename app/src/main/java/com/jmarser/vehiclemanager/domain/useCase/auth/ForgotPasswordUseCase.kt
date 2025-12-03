package com.jmarser.vehiclemanager.domain.useCase.auth

import com.jmarser.vehiclemanager.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Project: Vehicle manager
 * File: ForgotPasswordUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/12/2025
 */

class ForgotPasswordUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    operator fun invoke(email: String): Flow<Result<Unit>> = repository.forgotPassword(email)
}