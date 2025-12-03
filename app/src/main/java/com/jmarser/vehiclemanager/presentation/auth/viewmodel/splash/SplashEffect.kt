package com.jmarser.vehiclemanager.presentation.auth.viewmodel.splash

/**
 * Project: Vehicle manager
 * File: SplashEffect
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/12/2025
 */

sealed interface SplashEffect {

    object NavigateToHome : SplashEffect
    object NavigateToLogin : SplashEffect
    data class ShowToast(val message: String) : SplashEffect
}