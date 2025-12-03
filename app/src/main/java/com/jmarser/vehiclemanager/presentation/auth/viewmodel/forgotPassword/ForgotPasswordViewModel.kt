package com.jmarser.vehiclemanager.presentation.auth.viewmodel.forgotPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.utils.ResourceProvider
import com.jmarser.vehiclemanager.domain.useCase.ValidationFormUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
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
class ForgotPasswordViewModel @Inject constructor(
    private val validateForm: ValidationFormUseCase,
    private val resource: ResourceProvider
): ViewModel(){

    private val _formState = MutableStateFlow(ForgotPasswordState())
    val formState: StateFlow<ForgotPasswordState> = _formState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<ForgotPasswordEffect>()
    val uiEffect: SharedFlow<ForgotPasswordEffect> = _uiEffect.asSharedFlow()

    fun onEvent(event: ForgotPasswordEvent){
        when(event){
            is ForgotPasswordEvent.SetEmail -> setEmail(event.email)
            ForgotPasswordEvent.OnBackClick -> emitEffect(ForgotPasswordEffect.NavigateToLogin)
            ForgotPasswordEvent.OnForgotPasswordClick -> forgotPassword()
        }
    }

    private fun setEmail(email: String){
        val isValid = validateForm.validateEmail(email)
        _formState.value = _formState.value.copy(
            email = email,
            isEmailValid = isValid,
            emailErrorMessage = if (isValid) null else R.string.error_email_invalid
        )

        validateSubmit()
    }

    private fun validateSubmit(){
        _formState.update {
            it.copy(
                isButtonEnabled = validateForm.validateFields(_formState.value.isEmailValid)
            )
        }
    }

    private fun clearForm(){
        _formState.value = ForgotPasswordState()
    }

    private fun emitEffect(effect: ForgotPasswordEffect){
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }

    private fun forgotPassword(){

    }
}