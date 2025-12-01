package com.jmarser.vehiclemanager.core.domain.validation

import androidx.annotation.StringRes

/**
 * Project: Vehicle manager
 * File: ValidationForm
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 01/12/2025
 */

interface ValidationForm {
    fun validateFieldNotEmpty(texto: String): Boolean
    fun validateEmail(email: String): Boolean
    fun validatePassword(password: String): Boolean
    fun validateConfirmPassword(password: String, confirmPassword: String): Boolean

    fun validatePasswordDetails(password: String): PasswordValidationResult
    fun validateFields(vararg validations: Boolean?): Boolean

    fun validHasUpperCase(password: String): Boolean
    fun validHasLowerCase(password: String): Boolean
    fun validHasNumber(password: String): Boolean
    fun validHasSpecialChar(password: String): Boolean
    fun validLength(password: String): Boolean
    fun validNotHasSpaces(password: String): Boolean
}

data class PasswordValidationResult(
    val isValid: Boolean,
    @StringRes val errorMessage: Int? = null
)