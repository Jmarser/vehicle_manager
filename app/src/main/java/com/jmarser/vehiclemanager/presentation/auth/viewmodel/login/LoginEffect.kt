package com.jmarser.vehiclemanager.presentation.auth.viewmodel.login

/**
 * Project: Vehicle manager
 * File: LoginEffect
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/12/2025
 */

sealed interface LoginEffect {

    object NavigateToHome: LoginEffect
    object NavigateToRegister: LoginEffect
    object NavigateToForgotPassword: LoginEffect
    data class ShowToast(val message: String): LoginEffect
}