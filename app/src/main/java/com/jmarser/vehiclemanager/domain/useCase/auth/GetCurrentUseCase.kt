package com.jmarser.vehiclemanager.domain.useCase.auth

import com.jmarser.vehiclemanager.domain.repository.auth.AuthRepository
import javax.inject.Inject

/**
 * Project: Vehicle manager
 * File: GetCurrentUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/12/2025
 */

class GetCurrentUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke() = repository.getCurrentUser()
}