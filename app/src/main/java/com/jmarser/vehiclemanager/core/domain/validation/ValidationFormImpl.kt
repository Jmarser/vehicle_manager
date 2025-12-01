package com.jmarser.vehiclemanager.core.domain.validation

import com.jmarser.vehiclemanager.R

/**
 * Project: Vehicle manager
 * File: ValidationFormImpl
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 01/12/2025
 */

class ValidationFormImpl: ValidationForm {
    override fun validateFieldNotEmpty(texto: String): Boolean {
        return texto.isNotEmpty() && texto.isNotBlank()
    }

    override fun validateEmail(email: String): Boolean {
        val emailPattern = Regex(
            "^[A-Za-z0-9._%+-]+@" +
                    "(?!.*\\.\\.)" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*" +
                    "\\.[A-Za-z]{2,}$"
        )
        return emailPattern.matches(email)
    }

    override fun validatePassword(password: String): Boolean {
        val hasUpperCase = validHasUpperCase(password)
        val hasLowerCase = validHasLowerCase(password)
        val hasNumber = validHasNumber(password)
        val hasSpecialChar = validHasSpecialChar(password)
        val length = validLength(password)
        val notHasSpaces = validNotHasSpaces(password)

        return hasUpperCase && hasLowerCase && hasNumber && hasSpecialChar && length && notHasSpaces
    }

    override fun validateConfirmPassword(
        password: String,
        confirmPassword: String
    ): Boolean {
        return password == confirmPassword
    }

    override fun validatePasswordDetails(password: String): PasswordValidationResult {
        val errors = mutableListOf<Int>()

        if (!validHasUpperCase(password)) errors.add(R.string.error_uppercase_required)
        if (!validHasLowerCase(password)) errors.add(R.string.error_lowercase_required)
        if (!validHasNumber(password)) errors.add(R.string.error_number_required)
        if (!validHasSpecialChar(password)) errors.add(R.string.error_special_char_required)
        if (!validLength(password)) errors.add(R.string.error_min_length_required)
        if (!validNotHasSpaces(password)) errors.add(R.string.error_not_spaces_required)

        return PasswordValidationResult(
            isValid = errors.isEmpty(),
            errorMessage = errors.firstOrNull()
        )
    }

    override fun validateFields(vararg validations: Boolean?): Boolean {
        val areAllNotNull = validations.none { it == null }
        val areAllTrue = validations.all { it == true }

        return areAllNotNull && areAllTrue
    }

    override fun validHasUpperCase(password: String): Boolean {
        return password.any{it.isUpperCase()}
    }

    override fun validHasLowerCase(password: String): Boolean {
        return password.any { it.isLowerCase() }
    }

    override fun validHasNumber(password: String): Boolean {
        return password.any { it.isDigit() }
    }

    override fun validHasSpecialChar(password: String): Boolean {
        val regex = Regex("[!@#\$%^&*(),.?\":{}|<>]")
        return regex.containsMatchIn(password)
    }

    override fun validLength(password: String): Boolean {
        return password.length >= 6
    }

    override fun validNotHasSpaces(password: String): Boolean {
        return !password.contains(" ")
    }
}