package com.jmarser.vehiclemanager.presentation.auth.viewModel

import app.cash.turbine.test
import com.jmarser.vehiclemanager.core.utils.ResourceProvider
import com.jmarser.vehiclemanager.domain.useCase.ValidationFormUseCase
import com.jmarser.vehiclemanager.domain.useCase.auth.ForgotPasswordUseCase
import com.jmarser.vehiclemanager.presentation.MainDispatcherRule
import com.jmarser.vehiclemanager.presentation.auth.viewmodel.forgotPassword.ForgotPasswordEffect
import com.jmarser.vehiclemanager.presentation.auth.viewmodel.forgotPassword.ForgotPasswordEvent
import com.jmarser.vehiclemanager.presentation.auth.viewmodel.forgotPassword.ForgotPasswordViewModel
import com.jmarser.vehiclemanager.presentation.utils.ExceptionStringMapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Project: Vehicle manager
 * File: ForgotPasswordViewModelTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 24/12/2025
 */

class ForgotPasswordViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val validateForm: ValidationFormUseCase = mockk(relaxed = true)
    private val resource: ResourceProvider = mockk(relaxed = true)
    private val forgotPasswordUseCase: ForgotPasswordUseCase = mockk()
    private val stringMapper: ExceptionStringMapper = mockk()

    private lateinit var viewModel: ForgotPasswordViewModel

    @Before
    fun setUp() {
        viewModel = ForgotPasswordViewModel(
            validateForm,
            resource,
            forgotPasswordUseCase,
            stringMapper
        )
    }

    @Test
    fun `setEmail actualiza correctamente el estado del formulario`() {
        val email = "test@test.com"
        every { validateForm.validateEmail(email) } returns true

        viewModel.onEvent(ForgotPasswordEvent.SetEmail(email))

        val currentState = viewModel.formState.value
        assertEquals(email, currentState.email)
        assertTrue(currentState.isEmailValid == true)
    }

    @Test
    fun `setEmail actualiza el estado y valida el campo con el formato de email incorrecto`() {
        val email = "test"
        every { validateForm.validateEmail(email)} returns false

        viewModel.onEvent(ForgotPasswordEvent.SetEmail(email))

        val currentState = viewModel.formState.value
        assertEquals(email, currentState.email)
        assertFalse(currentState.isEmailValid == true)
        assertFalse(currentState.isButtonEnabled)
    }

    @Test
    fun `forgotPassword emite exito y muestra toast informativo`() = runTest {
        val email = "test@test.com"
        every { validateForm.validateEmail(email) } returns true
        viewModel.onEvent(ForgotPasswordEvent.SetEmail(email))

        coEvery { forgotPasswordUseCase(email) } returns flowOf(Result.success(Unit))
        every { resource.getString(any()) } returns "Correo solicitado"

        viewModel.uiEffect.test {
            viewModel.onEvent(ForgotPasswordEvent.OnForgotPasswordClick)

            val effect = awaitItem()
            assertTrue(effect is ForgotPasswordEffect.ShowToast)
            assertEquals("Correo solicitado", (effect as ForgotPasswordEffect.ShowToast).message)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `forgotPassword emite error y muestra toast informativo`() = runTest {
        val exception = Exception("Error")
        coEvery { forgotPasswordUseCase(any()) } returns flowOf(Result.failure(exception))

        every { stringMapper.mapExceptionToResourceId(exception) } returns 1000
        every { resource.getString(1000) } returns "Credenciales inválidas. Verifica tu email y contraseña"

        viewModel.uiEffect.test {
            viewModel.onEvent(ForgotPasswordEvent.OnForgotPasswordClick)

            val effect = awaitItem()

            assertEquals("Credenciales inválidas. Verifica tu email y contraseña", (effect as ForgotPasswordEffect.ShowToast).message)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Realiza la navegación hacia atrás al pulsar el evento correspondiente`() = runTest {
        viewModel.uiEffect.test {
            viewModel.onEvent(ForgotPasswordEvent.OnBackClick)

            val effect = awaitItem()

            assertTrue(effect is ForgotPasswordEffect.NavigateToLogin)
            cancelAndConsumeRemainingEvents()
        }
    }
 }