package com.jmarser.vehiclemanager.domain.validation.useCase.auth

import com.jmarser.vehiclemanager.domain.repository.auth.AuthRepository
import com.jmarser.vehiclemanager.domain.useCase.auth.ForgotPasswordUseCase
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
 * File: ResetPasswordUseCaseTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 18/12/2025
 */

class ForgotPasswordUseCaseTest {

    private val repository = mockk<AuthRepository>()
    private lateinit var useCase: ForgotPasswordUseCase

    @Before
    fun setUp() {
        useCase = ForgotPasswordUseCase(repository)
    }

    @Test
    fun `invoke retorna resultado correcto`()  = runTest{
        coEvery { repository.forgotPassword("test@test.com") } returns flow {
            emit(Result.success(Unit))
        }

        val result = useCase("test@test.com").first()

        assertTrue(result.isSuccess)

    }

    @Test
    fun `invoke retorna error en una excepción`() = runTest{
        val exception = Exception("ForgotPassword error")

        coEvery { repository.forgotPassword("test@test.com") } returns flow {
            emit(Result.failure(exception))
        }

        val result = useCase("test@test.com").first()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
