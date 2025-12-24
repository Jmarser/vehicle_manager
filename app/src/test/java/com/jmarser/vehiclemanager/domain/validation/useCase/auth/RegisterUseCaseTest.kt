package com.jmarser.vehiclemanager.domain.validation.useCase.auth

import com.jmarser.vehiclemanager.domain.model.User
import com.jmarser.vehiclemanager.domain.repository.auth.AuthRepository
import com.jmarser.vehiclemanager.domain.useCase.auth.RegisterUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Project: Vehicle manager
 * File: RegisterUseCaseTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 18/12/2025
 */

class RegisterUseCaseTest {

    private val repository = mockk<AuthRepository>()
    private lateinit var useCase: RegisterUseCase

    @Before
    fun setUp() {
        useCase = RegisterUseCase(repository)
    }

    @Test
    fun `invoke devuelve success y un user con registro correcto`() = runTest{
        val user = User(
            id = "123456",
            email = "test@test.com",
            name = "test",
        )

        coEvery { repository.register(any()) } returns flow {
            emit(Result.success(user))
        }

        val result = useCase("test", "test@test.com", "123456").first()

        assertTrue {result.isSuccess }
        assertEquals(user, result.getOrNull())
    }

    @Test
    fun `invoke retorna fallo con una excepción`() = runTest {
        val exception = Exception("Register error")

        coEvery { repository.register(any()) } returns flow {
            emit(Result.failure(exception))
        }

        val result = useCase("test", "test@test.com", "123456").first()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}