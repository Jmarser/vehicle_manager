package com.jmarser.vehiclemanager.presentation.auth.viewmodel.register

/**
 * Project: Vehicle manager
 * File: RegisterEffect
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 01/12/2025
 */

sealed interface RegisterEffect {

    object NavigateToHome: RegisterEffect
    object NavigateToBack: RegisterEffect
    data class ShowToast(val message: String): RegisterEffect
}