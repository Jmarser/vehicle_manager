package com.jmarser.vehiclemanager.presentation.auth.viewmodel.forgotPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.utils.ResourceProvider
import com.jmarser.vehiclemanager.domain.useCase.ValidationFormUseCase
import com.jmarser.vehiclemanager.domain.useCase.auth.ForgotPasswordUseCase
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
 * File: ForgotPasswordViewModel
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/12/2025
 */

@HiltViewModel
class ForgotPasswordViewModel
    @Inject
    constructor(
        private val validateForm: ValidationFormUseCase,
        private val resource: ResourceProvider,
        private val forgotPasswordUseCase: ForgotPasswordUseCase,
        private val stringMapper: ExceptionStringMapper
    ) : ViewModel() {
        private val _formState = MutableStateFlow(ForgotPasswordState())
        val formState: StateFlow<ForgotPasswordState> = _formState.asStateFlow()

        private val _uiEffect = MutableSharedFlow<ForgotPasswordEffect>()
        val uiEffect: SharedFlow<ForgotPasswordEffect> = _uiEffect.asSharedFlow()

        fun onEvent(event: ForgotPasswordEvent) {
            when (event) {
                is ForgotPasswordEvent.SetEmail -> setEmail(event.email)
                ForgotPasswordEvent.OnBackClick -> emitEffect(ForgotPasswordEffect.NavigateToLogin)
                ForgotPasswordEvent.OnForgotPasswordClick -> forgotPassword()
            }
        }

        private fun setEmail(email: String) {
            val isValid = validateForm.validateEmail(email)
            _formState.value =
                _formState.value.copy(
                    email = email,
                    isEmailValid = isValid,
                    emailErrorMessage = if (isValid) null else R.string.error_email_invalid,
                )

            validateSubmit()
        }

        private fun validateSubmit() {
            _formState.update {
                it.copy(
                    isButtonEnabled = validateForm.validateFields(_formState.value.isEmailValid),
                )
            }
        }

        private fun clearForm() {
            _formState.value = ForgotPasswordState()
        }

        private fun emitEffect(effect: ForgotPasswordEffect) {
            viewModelScope.launch {
                _uiEffect.emit(effect)
            }
        }

        private fun forgotPassword() {
            forgotPasswordUseCase(_formState.value.email)
                .onStart { _formState.update { it.copy(isLoading = true) } }
                .onEach { result ->
                    clearForm()
                    result
                        .onSuccess { data ->
                            emitEffect(ForgotPasswordEffect.ShowToast(resource.getString(R.string.reset_requested)))
                        }.onFailure { error ->
                            emitEffect(ForgotPasswordEffect.ShowToast(resource.getString(stringMapper.mapExceptionToResourceId(error))))
                        }
                }.catch {
                    clearForm()
                    emitEffect(ForgotPasswordEffect.ShowToast(resource.getString(R.string.error_unexpected_request)))
                }.launchIn(viewModelScope)
        }
    }
