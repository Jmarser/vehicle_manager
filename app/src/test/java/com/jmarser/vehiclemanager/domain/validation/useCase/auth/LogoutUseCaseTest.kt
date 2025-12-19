package com.jmarser.vehiclemanager.domain.validation.useCase.auth

import com.jmarser.vehiclemanager.domain.repository.auth.AuthRepository
import com.jmarser.vehiclemanager.domain.useCase.auth.LogoutUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

/**
 * Project: Vehicle manager
 * File: LogoutUseCaseTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 18/12/2025
 */

class LogoutUseCaseTest {

    private val repository = mockk<AuthRepository>()
    private lateinit var useCase: LogoutUseCase

    @Before
    fun setUp() {
        useCase = LogoutUseCase(repository)
    }

    @Test
    fun `invoke llama al repositorio correctamente`() = runTest {
        coEvery { repository.logout() } returns Unit

        useCase()

        assertTrue(true)
    }

    @Test(expected = Exception::class)
    fun `invoke lanza una excepción cuando el repositorio falla`() = runTest {
        coEvery { repository.logout() } throws java.lang.Exception("Logout error")

        useCase()
    }
}