package com.jmarser.vehiclemanager.domain.useCase.auth

import com.jmarser.vehiclemanager.domain.repository.auth.AuthRepository
import javax.inject.Inject

/**
 * Project: Vehicle manager
 * File: LogoutUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/12/2025
 */

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() = authRepository.logout()
}