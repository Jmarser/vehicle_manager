package com.jmarser.vehiclemanager.presentation.auth.viewmodel.forgotPassword

/**
 * Project: Vehicle manager
 * File: ForgotPasswordEffect
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/12/2025
 */

sealed interface ForgotPasswordEffect {

    object NavigateToLogin : ForgotPasswordEffect
    data class ShowToast(val message: String) : ForgotPasswordEffect

}