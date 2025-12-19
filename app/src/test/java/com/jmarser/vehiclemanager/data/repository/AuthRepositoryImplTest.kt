package com.jmarser.vehiclemanager.data.repository

import androidx.compose.runtime.referentialEqualityPolicy
import com.jmarser.vehiclemanager.data.dataSource.AuthRemoteDataSource
import com.jmarser.vehiclemanager.data.model.UserData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Project: Vehicle manager
 * File: AuthRepositoryImplTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 19/12/2025
 */

class AuthRepositoryImplTest {

    private lateinit var authRepositoryImpl: AuthRepositoryImpl
    private val remoteDataSource: AuthRemoteDataSource = mockk()

    @Before
    fun setUp() {
        authRepositoryImpl = AuthRepositoryImpl(remoteDataSource)
    }

    // Test LOGIN
    @Test
    fun `login obtiene un User del dataSource`() = runTest {
        val userData = UserData(
            uid = "123456",
            email = "test@test.com",
            displayName = "Test User"
        )

        coEvery { remoteDataSource.login(any()) } returns flow {
            emit(Result.success(userData))
        }

        val result = authRepositoryImpl.login(mockk()).first()

        assertTrue { result.isSuccess }

        val user = result.getOrNull()
        assertNotNull(user)
        assertEquals("123456", user.id)
        assertEquals("test@test.com", user.email)
        assertEquals("Test User", user.name)

    }

    @Test
    fun `login devuelve error si dataSource falla`() = runTest {
        val exception = Exception("Error")

        coEvery { remoteDataSource.login(any()) } returns flow {
            emit(Result.failure(exception))
        }

        val result = authRepositoryImpl.login(mockk()).first()

        assertTrue { result.isFailure }
        assertEquals(exception, result.exceptionOrNull())
    }

    // Tests Registro

    @Test
    fun `registro de usuario devuelve un User del UserData del data Source`() = runTest {
        val userData = UserData(
            uid = "123456",
            email = "test@test.com",
            displayName = "Test User"
        )

        coEvery { remoteDataSource.register(any()) } returns flow {
            emit(Result.success(userData))
        }

        val result = authRepositoryImpl.register(mockk()).first()

        assertTrue { result.isSuccess }

        val user = result.getOrNull()
        assertNotNull(user)
        assertEquals("123456", user.id)
        assertEquals("test@test.com", user.email)
        assertEquals("Test User", user.name)
    }

    @Test
    fun `registro devuelve error si dataSource falla`() = runTest {
        val exception = Exception("Error")

        coEvery { remoteDataSource.register(any()) } returns flow {
            emit(Result.failure(exception))
        }

        val result = authRepositoryImpl.register(mockk()).first()

        assertTrue { result.isFailure }
        assertEquals(exception, result.exceptionOrNull())
    }

    // Test Session
    @Test
    fun `getCurrentUser devuelve un User si el UserData existe`() = runTest {
        val userData = UserData(
            uid = "123456",
            email = "test@test.com",
            displayName = "Test User"
        )

        coEvery { remoteDataSource.getCurrentUser() } returns userData

        val result = authRepositoryImpl.getCurrentUser()

        assertNotNull(result)
        assertEquals("123456", result.id)
        assertEquals("test@test.com", result.email)
        assertEquals("Test User", result.name)
    }

    @Test
    fun `getCurrentUser devuelve null si el UserData no existe`() = runTest {
        coEvery { remoteDataSource.getCurrentUser() } returns null

        val result = authRepositoryImpl.getCurrentUser()

        assertNull(result)
    }

    // Test Logout
    @Test
    fun `logout llama correctamente al dataSource`() = runTest {
        coEvery { remoteDataSource.logout() } returns Unit

        authRepositoryImpl.logout()

        coVerify { authRepositoryImpl.logout() }
    }

    // Test resetear password
    @Test
    fun `resetPassword llama correctamente al dataSource`() = runTest {
        coEvery { authRepositoryImpl.forgotPassword(any()) } returns flow {
            emit(Result.success(Unit))
        }

        val result = authRepositoryImpl.forgotPassword("test@test.com").first()

        assertTrue(result.isSuccess)
        assertEquals(Unit, result.getOrNull())
    }

    @Test
    fun `resetPassword devuelve error cuando el dataSource falla`() = runTest {
        val error = Exception("No existe el email")

        coEvery { remoteDataSource.forgotPassword(any()) } returns flow {
            emit(Result.failure(error))
        }

        val result = authRepositoryImpl.forgotPassword("test@test.com").first()

        assertTrue { result.isFailure }
        assertEquals(error, result.exceptionOrNull())
    }
}