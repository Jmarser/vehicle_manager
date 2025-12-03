package com.jmarser.vehiclemanager.presentation.auth.viewmodel.forgotPassword

/**
 * Project: Vehicle manager
 * File: ForgotPasswordEvent
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/12/2025
 */

sealed interface ForgotPasswordEvent {
    data class SetEmail(
        val email: String,
    ) : ForgotPasswordEvent

    object OnForgotPasswordClick : ForgotPasswordEvent

    object OnBackClick : ForgotPasswordEvent
}
