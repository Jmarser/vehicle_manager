package com.jmarser.vehiclemanager.domain.validation.useCase.auth

import com.jmarser.vehiclemanager.domain.model.User
import com.jmarser.vehiclemanager.domain.repository.auth.AuthRepository
import com.jmarser.vehiclemanager.domain.useCase.auth.GetCurrentUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Project: Vehicle manager
 * File: GetCurrentUseCaseTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 18/12/2025
 */

class GetCurrentUseCaseTest {
    private val repository = mockk<AuthRepository>()
    private lateinit var useCase: GetCurrentUseCase

    @Before
    fun setUp() {
        useCase = GetCurrentUseCase(repository)
    }

    @Test
    fun `invoke retorna un User con solicitud correcta`() = runTest {
        val expected = User("123", "test@test.com", "test")

        coEvery { repository.getCurrentUser() } returns expected

        val result = useCase()

        assertEquals(expected, result)
    }

    @Test
    fun `invoke retorna null cuando el repositorio devuelve null`() = runTest {
        coEvery { repository.getCurrentUser() } returns null

        val result = useCase()

        assertNull(result)
    }

}