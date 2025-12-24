package com.jmarser.vehiclemanager.domain.validation.useCase.auth

import com.jmarser.vehiclemanager.domain.model.User
import com.jmarser.vehiclemanager.domain.repository.auth.AuthRepository
import com.jmarser.vehiclemanager.domain.useCase.auth.LoginUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Project: Vehicle manager
 * File: LoginUseCaseTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 18/12/2025
 */

class LoginUseCaseTest {

    private val repository = mockk<AuthRepository>()
    private lateinit var useCase: LoginUseCase

    @Before
    fun setUp() {
        useCase = LoginUseCase(repository)
    }

    @Test
    fun invoke_returns_success_User() = runTest {
        val user = User(
            id = "123456",
            email = "test@test.com",
            name = "test"
        )

        coEvery { repository.login(any()) } returns flow {
            emit(Result.success(user))
        }

        val result = useCase("test@test.com", "123456").first()

        assertTrue { result.isSuccess }
        assertEquals(user, result.getOrNull())
    }

    @Test
    fun `ìnvoke debe devolver fallo cuando el repositorio emite un error`() = runTest {
        val exception = Exception("Login error")
        coEvery { repository.login(any()) } returns flow {
            emit(Result.failure(exception))
        }

        val result = useCase("test@test.com", "123456").first()

        assertTrue { result.isFailure }
        assertEquals(exception, result.exceptionOrNull())
    }
}