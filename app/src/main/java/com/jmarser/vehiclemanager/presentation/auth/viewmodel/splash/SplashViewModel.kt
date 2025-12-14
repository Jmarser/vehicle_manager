package com.jmarser.vehiclemanager.presentation.auth.viewmodel.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.utils.ResourceProvider
import com.jmarser.vehiclemanager.domain.useCase.auth.GetCurrentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: Vehicle manager
 * File: SplashViewModel
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 03/12/2025
 */

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val resource: ResourceProvider,
    private val getUser: GetCurrentUseCase
): ViewModel(){

    private val _uiEffect = MutableSharedFlow<SplashEffect>(replay = 2)
    val uiEffect: SharedFlow<SplashEffect> = _uiEffect.asSharedFlow()

    init {
        getCurrentUser()
    }

    private fun getCurrentUser(){
        viewModelScope.launch {
            val user = getUser()
            user?.let {
                _uiEffect.emit(SplashEffect.ShowToast(resource.getString(R.string.login_successfull)))
                _uiEffect.emit(SplashEffect.NavigateToLogin)
            } ?: run {
                _uiEffect.emit(SplashEffect.NavigateToLogin)
            }
        }
    }
}