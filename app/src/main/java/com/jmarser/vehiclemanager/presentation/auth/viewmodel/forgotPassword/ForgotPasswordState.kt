package com.jmarser.vehiclemanager.presentation.auth.viewmodel.forgotPassword

import androidx.annotation.StringRes

data class ForgotPasswordState(

    val email: String = "",

    @StringRes val emailErrorMessage: Int? = null,

    val isEmailValid: Boolean? = null,

    val isLoading: Boolean = false,

    val isButtonEnabled: Boolean = false
)
