package com.jmarser.vehiclemanager.core.domain.validation

import com.jmarser.vehiclemanager.R
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Project: Vehicle manager
 * File: ValidationFormTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 01/12/2025
 */

class ValidationFormTest {
    private lateinit var validation: ValidationFormImpl

    @Before
    fun setUp() {
        validation = ValidationFormImpl()
    }

    @Test
    fun validateFieldNotEmpty_returns_true_for_non_empty() {
        assertTrue(validation.validateFieldNotEmpty("Test"))
    }

    @Test
    fun validateFieldNotEmpty_returns_false_for_empty() {
        assertFalse(validation.validateFieldNotEmpty(""))
    }

    @Test
    fun validateFieldNotEmpty_returns_false_for_blank() {
        assertFalse(validation.validateFieldNotEmpty("   "))
    }

    @Test
    fun validateEmail_returns_true_for_valid_email() {
        assertTrue(validation.validateEmail("test@test.com"))
        assertTrue(validation.validateEmail("test.test@test.com"))
        assertTrue(validation.validateEmail("test_name@test.com"))
        assertTrue(validation.validateEmail("test+alias@test.com"))
        assertTrue(validation.validateEmail("test-alias@test.com"))
        assertTrue(validation.validateEmail("test@test.co.es"))
    }

    @Test
    fun validateEmail_returns_false_for_invalid_email() {
        assertFalse(validation.validateEmail("test"))
        assertFalse(validation.validateEmail("test@"))
        assertFalse(validation.validateEmail("test@test"))
        assertFalse(validation.validateEmail("test@test."))
        assertFalse(validation.validateEmail("test@test.c"))
        assertFalse(validation.validateEmail("test@test.com."))
        assertFalse(validation.validateEmail("test@.com"))
        assertFalse(validation.validateEmail("@test.com"))
        assertFalse(validation.validateEmail("test@test..com"))
        assertFalse(validation.validateEmail("test@test .com"))
    }

    @Test
    fun validatePassword_returns_true_for_valid_password() {
        assertTrue(validation.validatePassword("Pass123!"))
    }

    @Test
    fun validatePassword_returns_false_for_invalid_password() {
        assertFalse(validation.validatePassword("pass123!"))
        assertFalse(validation.validatePassword("PASS123!"))
        assertFalse(validation.validatePassword("Password!"))
        assertFalse(validation.validatePassword("Pass123"))
        assertFalse(validation.validatePassword("Pass"))
        assertFalse(validation.validatePassword("Pass 123!"))
    }

    @Test
    fun validatePasswordDetails_returns_true_for_valid_password() {
        val result = validation.validatePasswordDetails("Pass123!")
        assertTrue(result.isValid)
        assertNull(result.errorMessage)
    }

    @Test
    fun validatePasswordDetails_returns_false_for_invalid_password() {
        val result = validation.validatePasswordDetails("Pass123")
        assertFalse(result.isValid)
        assertNotNull(result.errorMessage)
    }

    @Test
    fun validatePasswordDetails_returns_false_for_invalid_password_with_error_message() {
        val resulUpper = validation.validatePasswordDetails("pass123!")
        assertEquals(R.string.error_uppercase_required, resulUpper.errorMessage)
        val resultLower = validation.validatePasswordDetails("PASS123!")
        assertEquals(R.string.error_lowercase_required, resultLower.errorMessage)
        val resultDigit = validation.validatePasswordDetails("Password!")
        assertEquals(R.string.error_number_required, resultDigit.errorMessage)
        val resultSpecial = validation.validatePasswordDetails("Pass123")
        assertEquals(R.string.error_special_char_required, resultSpecial.errorMessage)
        val resultHasSpaces = validation.validatePasswordDetails("Pass 123!")
        assertEquals(R.string.error_not_spaces_required, resultHasSpaces.errorMessage)
        val resultLength = validation.validatePasswordDetails("Pa1!")
        assertEquals(R.string.error_min_length_required, resultLength.errorMessage)
    }

    @Test
    fun validateConfirmPasswordDetails_returns_true_for_valid_password() {
        assertTrue(validation.validateConfirmPassword("Pass123!", "Pass123!"))
    }

    @Test
    fun validateConfirmPasswordDetails_returns_false_for_invalid_password() {
        assertFalse(validation.validateConfirmPassword("Pass123!", "Passw12!"))
    }

    @Test
    fun validateHasUpper_valid_and_invalid() {
        assertTrue(validation.validHasUpperCase("Password1#"))
        assertFalse(validation.validHasUpperCase("password1#"))
    }

    @Test
    fun validateHasLower_valid_and_invalid() {
        assertTrue(validation.validHasLowerCase("Password1#"))
        assertFalse(validation.validHasLowerCase("PASSWORD1#"))
    }

    @Test
    fun validateHasDigit_valid_and_invalid() {
        assertTrue(validation.validHasNumber("Password1#"))
        assertFalse(validation.validHasNumber("Password#"))
    }

    @Test
    fun validateHasSpecial_valid_and_invalid() {
        assertTrue(validation.validHasSpecialChar("Password1#"))
        assertFalse(validation.validHasSpecialChar("Password1"))
    }

    @Test
    fun validateLength_valid_and_invalid() {
        assertTrue(validation.validLength("Password1#"))
        assertFalse(validation.validLength("Pa1#"))
    }

    @Test
    fun validateVeryLongPassword_is_valid() {
        val longPassword = "Password1#" + "a".repeat(100)
        assertTrue(validation.validLength(longPassword))
    }

    @Test
    fun validateFields_all_fields_are_true_returns_true() {
        val result = validation.validateFields(true, true, true)
        assertTrue(result)
    }

    @Test
    fun validateFields_one_field_is_false_returns_false() {
        val result = validation.validateFields(true, false, true)
        assertFalse(result)
    }

    @Test
    fun validateFields_all_fields_are_false_returns_false() {
        val result = validation.validateFields(false, false, false)
        assertFalse(result)
    }

    @Test
    fun validateFields_one_field_is_null_returns_false() {
        val result = validation.validateFields(true, null, true)
        assertFalse(result)
    }

    @Test
    fun validateFields_all_fields_are_null_returns_false() {
        val result = validation.validateFields(null, null, null)
        assertFalse(result)
    }

    @Test
    fun validateFields_a_combinated_of_cases_returns_false() {
        val result = validation.validateFields(true, false, null)
        assertFalse(result)
    }

    @Test
    fun validateFields_a_single_field_is_true_returns_true() {
        val result = validation.validateFields(true)
        assertTrue(result)
    }

    @Test
    fun validateFields_a_single_field_is_false_returns_false() {
        val result = validation.validateFields(false)
        assertFalse(result)
    }

    @Test
    fun validateFields_a_single_field_is_null_returns_false() {
        val result = validation.validateFields(null)
        assertFalse(result)
    }
}
