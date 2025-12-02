package com.jmarser.vehiclemanager.presentation.auth.viewmodel.login

/**
 * Project: Vehicle manager
 * File: LoginEvent
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/12/2025
 */

sealed interface LoginEvent {
    data class SetEmail(val email: String) : LoginEvent
    data class SetPassword(val password: String) : LoginEvent
    object OnForgotPasswordClick : LoginEvent
    object OnLoginClick : LoginEvent
    object OnRegisterClick : LoginEvent
}