package com.jmarser.vehiclemanager.presentation.auth.viewmodel.register

/**
 * Project: Vehicle manager
 * File: RegisterEvent
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 01/12/2025
 */

sealed interface RegisterEvent {

    data class SetName(val name: String): RegisterEvent
    data class SetEmail(val email: String): RegisterEvent
    data class SetPassword(val password: String): RegisterEvent
    data class SetRepeatPassword(val repeatPassword: String): RegisterEvent
    object OnRegisterClick: RegisterEvent
    object OnBackClick: RegisterEvent
}