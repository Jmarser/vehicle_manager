package com.jmarser.vehiclemanager.presentation.utils

import com.jmarser.vehiclemanager.domain.exceptions.AutheticationException

/**
 * Project: Vehicle manager
 * File: ExceptionStringMapper
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 14/12/2025
 */

class ExceptionStringMapper {

    fun mapExceptionToResourceId(exception: Throwable): Int{
        return when(exception){
            is AutheticationException.InvalidCredentials -> R.string.error_invalid_credentials
            AutheticationException.UserNotFound -> R.string.error_user_not_found
            AutheticationException.NetworkError -> R.string.error_network_connection
            AutheticationException.EmailAlreadyInUse -> R.string.error_email_already_in_use
            AutheticationException.WeakPassword -> R.string.error_weak_password
            is AutheticationException.Unknown -> R.string.error_unknown_authentication
            else -> R.string.error_unknown_authentication
        }
    }

    //Simulador de id's de recursos para usar en los testing
    private object R {
        object string {
            const val error_invalid_credentials = 1000
            const val error_user_not_found = 1001
            const val error_network_connection = 1002
            const val error_email_already_in_use = 1003
            const val error_weak_password = 1004
            const val error_unknown_authentication = 1005
            const val error_generic = 9999
        }
    }
}