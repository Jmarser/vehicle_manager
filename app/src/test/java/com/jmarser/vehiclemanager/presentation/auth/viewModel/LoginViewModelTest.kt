package com.jmarser.vehiclemanager.presentation.auth.viewModel

import app.cash.turbine.test
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.domain.validation.PasswordValidationResult
import com.jmarser.vehiclemanager.core.utils.ResourceProvider
import com.jmarser.vehiclemanager.domain.model.User
import com.jmarser.vehiclemanager.domain.useCase.ValidationFormUseCase
import com.jmarser.vehiclemanager.domain.useCase.auth.LoginUseCase
import com.jmarser.vehiclemanager.presentation.MainDispatcherRule
import com.jmarser.vehiclemanager.presentation.auth.viewmodel.login.LoginEffect
import com.jmarser.vehiclemanager.presentation.auth.viewmodel.login.LoginEvent
import com.jmarser.vehiclemanager.presentation.auth.viewmodel.login.LoginViewModel
import com.jmarser.vehiclemanager.presentation.utils.ExceptionStringMapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Project: Vehicle manager
 * File: LoginViewModelTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 23/12/2025
 */

class LoginViewModelTest {

    @get:org.junit.Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val validateForm: ValidationFormUseCase = mockk(relaxed = true)
    private val resource: ResourceProvider = mockk(relaxed = true)
    private val loginUseCase: LoginUseCase = mockk()
    private val stringMapper: ExceptionStringMapper = mockk()

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        viewModel = LoginViewModel(
            validateForm,
            resource,
            loginUseCase,
            stringMapper
        )
    }

    @Test
    fun `setEmail actualiza el estado y valida el campo`() {
        val email = "test@test.com"
        every { validateForm.validateEmail(email) } returns true

        viewModel.onEvent(LoginEvent.SetEmail(email))

        val currentState = viewModel.formState.value
        assertEquals(email, currentState.email)
        assertTrue(currentState.isEmailValid == true)
    }

    @Test
    fun `setEmail actualiza el estado y valida el campo con el formato de email incorrecto`() {
        val email = "eamil-no-válido"
        every { validateForm.validateEmail(email) } returns false

        viewModel.onEvent(LoginEvent.SetEmail(email))

        val currentState = viewModel.formState.value
        assertEquals(email, currentState.email)
        assertFalse(currentState.isEmailValid == true)
    }

    @Test
    fun `setPassword actualiza el estado y valida el campo`() {
        val password = "Password123!"
        every { validateForm.validatePasswordDetails(password) } returns PasswordValidationResult(isValid = true, errorMessage = null)

        viewModel.onEvent(LoginEvent.SetPassword(password))

        val currentState = viewModel.formState.value
        assertEquals(password, currentState.password)
        assertTrue(currentState.isPasswordValid == true)
    }

    @Test
    fun `setPassword con requisitos insuficientes actualiza el estado con error`() {

        val weakPassword = "123"
        val validationResult = PasswordValidationResult(
            isValid = false,
            errorMessage = R.string.error_uppercase_required
        )

        every { validateForm.validatePasswordDetails(weakPassword) } returns validationResult

        viewModel.onEvent(LoginEvent.SetPassword(weakPassword))

        val currentState = viewModel.formState.value
        assertEquals(weakPassword, currentState.password)
        assertEquals(com.jmarser.vehiclemanager.R.string.error_uppercase_required, currentState.passwordErrorMessage)
        assertFalse(currentState.isPasswordValid == true)
    }

    @Test
    fun `tryToLogin emite estado success cuando las credenciales son válidas`() = runTest{
        val user = User(id = "1", name = "Test", email = "test@test.com")
        coEvery { loginUseCase(any(), any()) } returns flowOf(Result.success(user))
        every { resource.getString(any()) } returns "Test superado"

        viewModel.uiEffect.test {
            viewModel.onEvent(LoginEvent.OnLoginClick)

            val effect = awaitItem()

            assertTrue(effect is LoginEffect.ShowToast)
            assertTrue((effect as LoginEffect.ShowToast).message.contains("Test superado"))

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `tryToLogin maneja errores de autenticación correctamente`() = runTest {
        val exception = Exception("Error")
        coEvery { loginUseCase(any(), any()) } returns flowOf(Result.failure(exception))

        every { stringMapper.mapExceptionToResourceId(exception) } returns 1000
        every { resource.getString(1000) } returns "Credenciales inválidas. Verifica tu email y contraseña"

        viewModel.uiEffect.test {
            viewModel.onEvent(LoginEvent.OnLoginClick)

            val effect = awaitItem()

            assertEquals("Credenciales inválidas. Verifica tu email y contraseña", (effect as LoginEffect.ShowToast).message)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `El estado de carga se activa al iniciar el login`() = runTest {
        val resultFlow = flow{
            delay(100)
            emit(Result.success(User("1", "Test", "test@test.com")))
        }
        coEvery { loginUseCase(any(), any()) } returns resultFlow
        every { resource.getString(any()) } returns "Login exitoso"

        viewModel.formState.test {
            val initialState = awaitItem()
            assertFalse(initialState.isLoading)

            viewModel.onEvent(LoginEvent.OnLoginClick)

            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)

            val finalState = awaitItem()
            assertFalse(finalState.isLoading)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Realiza la navegación hacia el registro al pulsar el evento correspondiente`() = runTest {
        viewModel.uiEffect.test {
            viewModel.onEvent(LoginEvent.OnRegisterClick)

            val effect = awaitItem()
            assertTrue(effect is LoginEffect.NavigateToRegister)
        }
    }

    @Test
    fun `Realiza la navegación hacia recuperar contraseña al pulsar el evento correspondiente`() = runTest {
        viewModel.uiEffect.test {
            viewModel.onEvent(LoginEvent.OnForgotPasswordClick)

            val effect = awaitItem()
            assertTrue(effect is LoginEffect.NavigateToForgotPassword)
        }
    }

}