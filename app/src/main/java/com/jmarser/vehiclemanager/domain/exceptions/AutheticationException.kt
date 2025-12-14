package com.jmarser.vehiclemanager.domain.exceptions

/**
 * Project: Vehicle manager
 * File: AutheticationException
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 14/12/2025
 */

sealed class AutheticationException(
    cause: Throwable? = null
): DomainException(cause) {

    data class InvalidCredentials(
        override val cause: Throwable? = null
    ): AutheticationException(cause)

    data object UserNotFound: AutheticationException()

    data object NetworkError: AutheticationException()

    data object EmailAlreadyInUse: AutheticationException()

    data object WeakPassword: AutheticationException()

    data class Unknown(
        override val cause: Throwable? = null
    ): AutheticationException(cause)
}