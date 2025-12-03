package com.jmarser.vehiclemanager.presentation.auth.viewmodel.register

import androidx.annotation.StringRes

data class RegisterFormState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    @StringRes val nameErrorMessage: Int? = null,
    @StringRes val emailErrorMessage: Int? = null,
    @StringRes val passwordErrorMessage: Int? = null,
    @StringRes val confirmPasswordErrorMessage: Int? = null,
    val isNameValid: Boolean? = null,
    val isEmailValid: Boolean? = null,
    val isPasswordValid: Boolean? = null,
    val isConfirmPasswordValid: Boolean? = null,
    val isLoading: Boolean = false,
    val isButtonEnabled: Boolean = false,
)
