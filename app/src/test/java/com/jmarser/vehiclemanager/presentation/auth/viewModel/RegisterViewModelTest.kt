package com.jmarser.vehiclemanager.presentation.auth.viewModel

import app.cash.turbine.test
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.domain.validation.PasswordValidationResult
import com.jmarser.vehiclemanager.core.utils.ResourceProvider
import com.jmarser.vehiclemanager.domain.model.User
import com.jmarser.vehiclemanager.domain.useCase.ValidationFormUseCase
import com.jmarser.vehiclemanager.domain.useCase.auth.RegisterUseCase
import com.jmarser.vehiclemanager.presentation.MainDispatcherRule
import com.jmarser.vehiclemanager.presentation.auth.viewmodel.register.RegisterEffect
import com.jmarser.vehiclemanager.presentation.auth.viewmodel.register.RegisterEvent
import com.jmarser.vehiclemanager.presentation.auth.viewmodel.register.RegisterViewModel
import com.jmarser.vehiclemanager.presentation.utils.ExceptionStringMapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Project: Vehicle manager
 * File: RegisterViewModelTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 23/12/2025
 */

class RegisterViewModelTest {

    @get:org.junit.Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val validateForm: ValidationFormUseCase = mockk(relaxed = true)
    private val resource: ResourceProvider = mockk(relaxed = true)
    private val registerUseCase: RegisterUseCase = mockk()
    private val stringMapper: ExceptionStringMapper = mockk()

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        viewModel = RegisterViewModel(
            validateForm,
            registerUseCase,
            resource,
            stringMapper
        )
    }

    @Test
    fun `setName actualiza el estado y valida el campo`() {
        val name = "test"
        every { validateForm.validateFiledNotEmpty(name) } returns true

        viewModel.onEvent(RegisterEvent.SetName(name))

        val currentState = viewModel.formState.value
        assertEquals(name, currentState.name)
        assertTrue(currentState.isNameValid == true)
    }

    @Test
    fun `setName actualiza el estado y valida el campo con el campo vacio`() {
        val name = ""
        every { validateForm.validateFiledNotEmpty(name) } returns false

        viewModel.onEvent(RegisterEvent.SetName(name))

        val currentState = viewModel.formState.value
        assertEquals(name, currentState.name)
        assertFalse(currentState.isNameValid == true)
    }

    @Test
    fun `setEmail actualiza el estado y valida el campo`() {
        //GIVEN
        val email = "test@test.com"
        every { validateForm.validateEmail(email) } returns true

        //WHEN
        viewModel.onEvent(RegisterEvent.SetEmail(email))

        //THEN
        val currentState = viewModel.formState.value
        assertEquals(email, currentState.email)
        assertTrue(currentState.isEmailValid == true)
    }

    @Test
    fun `setEmail actualiza el estado y valida el campo con el formato de email incorrecto`() {
        val email = "email-no-valido"
        every { validateForm.validateEmail(email) } returns false

        viewModel.onEvent(RegisterEvent.SetEmail(email))

        val currentState = viewModel.formState.value
        assertEquals(email, currentState.email)
        assertFalse(currentState.isEmailValid == true)
    }

    @Test
    fun `setPassword actualiza el estado y valida el campo`() {
        val password = "Password123!"
        every { validateForm.validatePasswordDetails(password) } returns PasswordValidationResult(
            isValid = true,
            errorMessage = null
        )

        viewModel.onEvent(RegisterEvent.SetPassword(password))

        val currentState = viewModel.formState.value
        assertEquals(password, currentState.password)
        assertTrue(currentState.isPasswordValid == true)
        assertNull(currentState.passwordErrorMessage)
    }

    @Test
    fun `setPassword actualiza el estado y valida el campo con el password incorrecto`() {
        val password = "123"
        val validationResult = PasswordValidationResult(
            isValid = false,
            errorMessage = R.string.error_uppercase_required
        )

        every { validateForm.validatePasswordDetails(password) } returns validationResult

        viewModel.onEvent(RegisterEvent.SetPassword(password))

        val currentState = viewModel.formState.value
        assertEquals(password, currentState.password)
        assertFalse(currentState.isPasswordValid == true)
        assertEquals(validationResult.errorMessage, currentState.passwordErrorMessage)
    }

    @Test
    fun `setRepeatPassword actualiza el estado y valida el campo`() {
        val password = "Password123!"
        val repeatPassword = "Password123!"
        every { validateForm.validateConfirmPassword(password, repeatPassword) } returns true

        viewModel.onEvent(RegisterEvent.SetPassword(password))
        viewModel.onEvent(RegisterEvent.SetRepeatPassword(repeatPassword))

        val currentState = viewModel.formState.value
        assertEquals(repeatPassword, currentState.confirmPassword)
        assertTrue(currentState.isConfirmPasswordValid == true)
    }

    @Test
    fun `setRepeatPassword actualiza el estado y valida el campo con las contraseñas diferentes`() {
        val password = "Password123!"
        val repeatPassword = "Password123"
        every { validateForm.validateConfirmPassword(password, repeatPassword) } returns false

        viewModel.onEvent(RegisterEvent.SetRepeatPassword(repeatPassword))

        val currentState = viewModel.formState.value
        assertEquals(repeatPassword, currentState.confirmPassword)
        assertFalse(currentState.isConfirmPasswordValid == true)
    }

    @Test
    fun `tryToRegister emite el estado success cuando las credenciales son válidas`() = runTest {
        val user = User("1", "test@test.com", "test")
        coEvery { registerUseCase(any(), any(), any()) } returns flowOf(Result.success(user))
        every { resource.getString(any()) } returns "test superado"

        viewModel.uiEffect.test {
            viewModel.onEvent(RegisterEvent.OnRegisterClick)

            val effect = awaitItem()

            assertTrue(effect is RegisterEffect.ShowToast )
            assertTrue((effect as RegisterEffect.ShowToast).message.contains("test superado"))

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `tryToRegister emite el estado error cuando las credenciales son inválidas`() = runTest {
        val exception = Exception("Error de registro")
        coEvery { registerUseCase(any(), any(), any()) } returns flowOf(Result.failure(exception))
        every { stringMapper.mapExceptionToResourceId(exception)} returns 1000
        every { resource.getString(1000) } returns "Credenciales inválidas. Verifica tu email y contraseña"

        viewModel.uiEffect.test {
            viewModel.onEvent(RegisterEvent.OnRegisterClick)

            val effect = awaitItem()

            assertEquals("Credenciales inválidas. Verifica tu email y contraseña", (effect as RegisterEffect.ShowToast).message)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `El estado de carga se activa al iniciar el registro`() = runTest {
        val user = User("1", "test@test.com", "test")
        val resultFlow = flow {
            delay(100)
            emit(Result.success(user))
        }
        coEvery { registerUseCase(any(), any(), any()) } returns resultFlow
        every { resource.getString(any()) } returns "Registro exitoso"

        viewModel.formState.test {
            val initialState = awaitItem()
            assertFalse(initialState.isLoading)

            viewModel.onEvent(RegisterEvent.OnRegisterClick)

            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)

            val finalState =awaitItem()
            assertFalse(finalState.isLoading)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Realiza la navegación hacia atrás al pulsar el evento correspondiente`() = runTest{
        viewModel.uiEffect.test {
            viewModel.onEvent(RegisterEvent.OnBackClick)

            val effect = awaitItem()
            assertTrue(effect is RegisterEffect.NavigateToBack)
        }
    }
}