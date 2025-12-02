package com.jmarser.vehiclemanager.presentation.auth.viewmodel.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.domain.useCase.ValidationFormUseCase
import com.jmarser.vehiclemanager.domain.useCase.auth.RegisterUseCase
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
 * File: RegisterViewModel
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 01/12/2025
 */

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validationForm: ValidationFormUseCase,
    private val registerUseCase: RegisterUseCase
): ViewModel(){

    private val _formState = MutableStateFlow(RegisterFormState())
    val formState: StateFlow<RegisterFormState> = _formState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<RegisterEffect>()
    val uiEffect: SharedFlow<RegisterEffect> = _uiEffect.asSharedFlow()

    fun onEvent(event: RegisterEvent){
        when(event){
            is RegisterEvent.SetName -> setName(event.name)
            is RegisterEvent.SetEmail -> setEmail(event.email)
            is RegisterEvent.SetPassword -> setPassword(event.password)
            is RegisterEvent.SetRepeatPassword -> setRepeatPassword(event.repeatPassword)
            RegisterEvent.OnRegisterClick -> tryToRegister()
            RegisterEvent.OnBackClick -> onBackClick()
        }
    }

    private fun setName(name: String){
        val isValid = validationForm.validateFiledNotEmpty(name)
        _formState.update {
            it.copy(
                name = name,
                isNameValid = isValid,
                nameErrorMessage = if (isValid) null else R.string.error_name_user_not_empty
            )
        }
        validateButton()
    }

    private fun setEmail(email: String){
        val isValid = validationForm.validateEmail(email)
        _formState.update {
            it.copy(
                email = email,
                isEmailValid = isValid,
                emailErrorMessage = if (isValid) null else R.string.error_email_invalid
            )
        }
        validateButton()
    }

    private fun setPassword(password: String){
        val result = validationForm.validatePasswordDetails(password)
        _formState.update {
            it.copy(
                password = password,
                isPasswordValid = result.isValid,
                passwordErrorMessage = if (result.isValid) null else result.errorMessage
            )
        }
        validateButton()
    }

    private fun setRepeatPassword(repeatPassword: String){
        val isValid = validationForm.validateConfirmPassword(_formState.value.password, repeatPassword)
        _formState.update {
            it.copy(
                confirmPassword = repeatPassword,
                isConfirmPasswordValid = isValid,
                confirmPasswordErrorMessage = if (isValid) null else R.string.error_password_not_match
            )
        }
        validateButton()
    }

    private fun validateButton(){
        _formState.update {
            it.copy(
                isButtonEnabled = validationForm.validateFields(
                    _formState.value.isNameValid,
                    _formState.value.isEmailValid,
                    _formState.value.isPasswordValid,
                    _formState.value.isConfirmPasswordValid
                )
            )
        }
    }

    private fun onBackClick(){
        viewModelScope.launch {
            _uiEffect.emit(RegisterEffect.NavigateToBack)
        }
    }

    private fun clearForm(){
        _formState.value = RegisterFormState()
    }

    private fun tryToRegister(){
        registerUseCase(_formState.value.name, _formState.value.email, _formState.value.password)
            .onStart {
                _formState.update { it.copy(isLoading = true) }
            }
            .onEach { result ->
                _formState.update { it.copy(isLoading = false) }
                clearForm()
                result.onSuccess { data ->
                    _uiEffect.emit(RegisterEffect.ShowToast("Registro completado con éxito"))
                }.onFailure { error ->
                    _uiEffect.emit(RegisterEffect.ShowToast("Error en el registro: ${error.message}"))
                }
            }.catch {
                clearForm()
                _uiEffect.emit(RegisterEffect.ShowToast("Error inesperado en el registro"))
            }.launchIn(viewModelScope)
    }
}