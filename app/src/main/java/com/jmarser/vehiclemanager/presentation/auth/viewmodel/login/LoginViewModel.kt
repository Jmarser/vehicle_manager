package com.jmarser.vehiclemanager.presentation.auth.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.utils.ResourceProvider
import com.jmarser.vehiclemanager.domain.useCase.ValidationFormUseCase
import com.jmarser.vehiclemanager.domain.useCase.auth.LoginUseCase
import com.jmarser.vehiclemanager.presentation.utils.ExceptionStringMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: Vehicle manager
 * File: LoginViewModel
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/12/2025
 */

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val validateForm: ValidationFormUseCase,
    private val resource: ResourceProvider,
    private val loginUseCase: LoginUseCase,
    private val stringMapper: ExceptionStringMapper
) : ViewModel() {
    private val _formState = MutableStateFlow(LoginFormState())
    val formState: StateFlow<LoginFormState> = _formState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<LoginEffect>()
    val uiEffect: SharedFlow<LoginEffect> = _uiEffect.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.SetEmail -> setEmail(event.email)
            is LoginEvent.SetPassword -> setPassword(event.password)
            LoginEvent.OnLoginClick -> tryToLogin()
            LoginEvent.OnForgotPasswordClick -> emitEffect(LoginEffect.NavigateToForgotPassword)
            LoginEvent.OnRegisterClick -> emitEffect(LoginEffect.NavigateToRegister)
        }
    }

    private fun setEmail(email: String) {
        val isValid = validateForm.validateEmail(email)

        _formState.update {
            it.copy(
                email = email,
                isEmailValid = isValid,
                emailErrorMessage = if (isValid) null else R.string.error_email_invalid,
            )
        }

        validateSubmit()
    }

    private fun setPassword(password: String) {
        val result = validateForm.validatePasswordDetails(password)

        _formState.update {
            it.copy(
                password = password,
                isPasswordValid = result.isValid,
                passwordErrorMessage = if (result.isValid) null else result.errorMessage,
            )
        }

        validateSubmit()
    }

    private fun validateSubmit() {
        _formState.update {
            it.copy(
                isButtonEnabled =
                    validateForm.validateFields(
                        _formState.value.isEmailValid,
                        _formState.value.isPasswordValid,
                    ),
            )
        }
    }

    private fun clearForm() {
        _formState.value = LoginFormState()
    }

    private fun emitEffect(effect: LoginEffect) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }

    private fun tryToLogin() {
        loginUseCase(_formState.value.email, _formState.value.password)
            .onStart {
                _formState.update { it.copy(isLoading = true) }
            }.onEach { result ->
                clearForm()
                _formState.update { it.copy(isLoading = false) }
                result
                    .onSuccess { data ->
                        emitEffect(LoginEffect.ShowToast("${resource.getString(R.string.login_successfull)} para ${data.name}"))
                    }.onFailure { error ->
                        val resourceId = stringMapper.mapExceptionToResourceId(error)
                        val errorMessage = resource.getString(resourceId)
                        emitEffect(LoginEffect.ShowToast(errorMessage))
                    }
            }.catch {
                clearForm()
                emitEffect(LoginEffect.ShowToast(resource.getString(R.string.error_unexpected_login)))
            }.launchIn(viewModelScope)
    }
}
