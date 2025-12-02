package com.jmarser.vehiclemanager.presentation.auth.viewmodel.login

import androidx.annotation.StringRes

data class LoginFormState(
    val email: String = "",
    val password: String = "",

    @StringRes val emailErrorMessage: Int? = null,
    @StringRes val passwordErrorMessage: Int? = null,

    val isEmailValid: Boolean? = null,
    val isPasswordValid: Boolean? = null,

    val isLoading: Boolean = false,
    val isButtonEnabled: Boolean = false
)
