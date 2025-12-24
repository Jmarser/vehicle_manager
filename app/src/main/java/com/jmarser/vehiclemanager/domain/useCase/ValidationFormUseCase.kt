package com.jmarser.vehiclemanager.domain.useCase

import com.jmarser.vehiclemanager.core.domain.validation.PasswordValidationResult
import com.jmarser.vehiclemanager.core.domain.validation.ValidationForm
import javax.inject.Inject

/**
 * Project: Vehicle manager
 * File: ValidationFormUseCase
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 01/12/2025
 */

class ValidationFormUseCase
    @Inject
    constructor(
        private val validationForm: ValidationForm,
    ) {
        fun validateFiledNotEmpty(texto: String): Boolean = validationForm.validateFieldNotEmpty(texto)

        fun validateEmail(email: String): Boolean = validationForm.validateEmail(email)

        fun validatePassword(password: String): Boolean = validationForm.validatePassword(password)

        fun validateConfirmPassword(
            password: String,
            confirmPassword: String,
        ): Boolean = validationForm.validateConfirmPassword(password, confirmPassword)

        fun validatePasswordDetails(password: String): PasswordValidationResult = validationForm.validatePasswordDetails(password)

        fun validateFields(vararg validations: Boolean?): Boolean = validationForm.validateFields(*validations)
    }
