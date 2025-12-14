package com.jmarser.vehiclemanager.domain.exceptions

/**
 * Project: Vehicle manager
 * File: DomainException
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 14/12/2025
 */

sealed class DomainException(
    override val cause: Throwable? = null
): Exception(cause)