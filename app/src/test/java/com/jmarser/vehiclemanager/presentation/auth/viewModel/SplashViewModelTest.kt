package com.jmarser.vehiclemanager.presentation.auth.viewModel

import app.cash.turbine.test
import com.jmarser.vehiclemanager.core.utils.ResourceProvider
import com.jmarser.vehiclemanager.domain.model.User
import com.jmarser.vehiclemanager.domain.useCase.auth.GetCurrentUseCase
import com.jmarser.vehiclemanager.presentation.MainDispatcherRule
import com.jmarser.vehiclemanager.presentation.auth.viewmodel.splash.SplashEffect
import com.jmarser.vehiclemanager.presentation.auth.viewmodel.splash.SplashViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Project: Vehicle manager
 * File: SplashViewModelTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 24/12/2025
 */

class SplashViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val resource: ResourceProvider = mockk(relaxed = true)
    private val getUser: GetCurrentUseCase = mockk()

    private lateinit var viewModel: SplashViewModel

    @Test
    fun `Cuando existe un usuario se emite un toast de exito y navega a la pantalla principal`() =
        runTest {
            val user = User("1", "test@test.com", "Test")
            coEvery { getUser() } returns user
            coEvery { resource.getString(any()) } returns "Bienvenido Test"

            viewModel = SplashViewModel(resource, getUser)

            viewModel.uiEffect.test {
                val firstEffect = awaitItem()
                assertTrue(firstEffect is SplashEffect.ShowToast)

                val secondEffect = awaitItem()
                assertTrue(secondEffect is SplashEffect.NavigateToLogin)
            }
        }

    @Test
    fun `Cuando no existe un usuario se emite un toast de error y navega a la pantalla de login`() =
        runTest {
            coEvery { getUser() } returns null
            coEvery { resource.getString(any()) } returns "No existe un usuario"

            viewModel = SplashViewModel(resource, getUser)

            viewModel.uiEffect.test {

                val secondEffect = awaitItem()
                assertTrue(secondEffect is SplashEffect.NavigateToLogin)
            }
        }
}