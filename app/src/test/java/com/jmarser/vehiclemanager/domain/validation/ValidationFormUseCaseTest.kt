package com.jmarser.vehiclemanager.domain.validation

import com.jmarser.vehiclemanager.core.domain.validation.PasswordValidationResult
import com.jmarser.vehiclemanager.core.domain.validation.ValidationForm
import com.jmarser.vehiclemanager.domain.useCase.ValidationFormUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import com.jmarser.vehiclemanager.R

/**
 * Project: Vehicle manager
 * File: ValidationFormUseCaseTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 01/12/2025
 */

class ValidationFormUseCaseTest {

    private val validationForm = mockk<ValidationForm>()
    private lateinit var useCase: ValidationFormUseCase

    @Before
    fun setUp() {
        useCase = ValidationFormUseCase(validationForm)
    }

    @Test
    fun validateFieldNotEmpty_returns_correct_value(){
        every { validationForm.validateFieldNotEmpty("Test") } returns true
        val result = useCase.validateFiledNotEmpty("Test")
        assertTrue(result)
    }

    @Test
    fun validateFieldNotEmpty_returns_false(){
        every { validationForm.validateFieldNotEmpty("") } returns false
        val result = useCase.validateFiledNotEmpty("")
        assertFalse(result)
    }

    @Test
    fun validateEmail_returns_true(){
        every { validationForm.validateEmail("test@test.com") } returns true
        val result = useCase.validateEmail("test@test.com")
        assertTrue(result)
    }

    @Test
    fun validateEmail_returns_false(){
        every { validationForm.validateEmail("test") } returns false
        val result = useCase.validateEmail("test")
        assertFalse(result)
    }

    @Test
    fun validatePassword_returns_true(){
        every { validationForm.validatePassword("Pass123!") } returns true
        val result = useCase.validatePassword("Pass123!")
        assertTrue(result)
    }

    @Test
    fun validatePassword_returns_false(){
        every { validationForm.validatePassword("pass") } returns false
        val result = useCase.validatePassword("pass")
        assertFalse(result)
    }

    @Test
    fun validateConfirmPassword_returns_true(){
        every { validationForm.validateConfirmPassword("Pass123!", "Pass123!") } returns true
        val result = useCase.validateConfirmPassword("Pass123!", "Pass123!")
        assertTrue(result)
    }

    @Test
    fun validateConfirmPassword_returns_false(){
        every { validationForm.validateConfirmPassword("Pass123!", "Passw123!")} returns false
        val result = useCase.validateConfirmPassword("Pass123!", "Passw123!")
        assertFalse(result)
    }

    @Test
    fun validatePasswordDetails_return_complete_passwordValidationResult_true(){
        val expected = PasswordValidationResult(
            isValid = true,
            errorMessage = null
        )
        every { validationForm.validatePasswordDetails("Pass123!") } returns expected
        assertEquals(expected, useCase.validatePasswordDetails("Pass123!"))
    }

    @Test
    fun validatePasswordDetails_return_complete_passwordValidationResult_false(){
        val expected = PasswordValidationResult(
            isValid = false,
            errorMessage = R.string.error_number_required
        )
        every { validationForm.validatePasswordDetails("Password!") } returns expected
        assertEquals(expected, useCase.validatePasswordDetails("Password!"))
    }

    @Test
    fun validateFields_returns_true_when_all_are_valid(){
        every { validationForm.validateFields(true, true, true) } returns true
        val result = useCase.validateFields(true, true, true)
        assertTrue(result)
    }


    @Test
    fun validateFields_returns_false_when_one_is_invalid() {
        every { validationForm.validateFields(true, false, true) } returns false
        val result = useCase.validateFields(true, false, true)
        assertFalse(result)
    }

    @Test
    fun validateFields_returns_false_when_one_is_null() {
        every { validationForm.validateFields(true, null, true) } returns false
        val result = useCase.validateFields(true, null, true)
        assertFalse(result)
    }

}