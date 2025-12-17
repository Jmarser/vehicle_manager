package com.jmarser.vehiclemanager.data.mappers

import com.google.firebase.auth.FirebaseUser
import com.jmarser.vehiclemanager.data.mapper.toUserData
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Project: Vehicle manager
 * File: FirebaseToUserDataTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 17/12/2025
 */

class FirebaseToUserDataTest {

    @Test
    fun `toUserData debería mapear FirebaseUser a UserData correctamente`() {

        val firebaseUser = mockk<FirebaseUser>(relaxed = true)

        every { firebaseUser.uid } returns "123456"
        every { firebaseUser.email } returns "test@test.com"
        every { firebaseUser.displayName } returns "Test User"

        val userData = firebaseUser.toUserData()

        assertEquals("123456", userData.uid, "El uid debería ser 123456")
        assertEquals("test@test.com", userData.email, "El email debería ser test@test.com")
        assertEquals("Test User", userData.displayName, "El displayName debería ser Test User")
    }

    @Test
    fun `toUserData debería transformar displayName null a cadena vacía`() {

        val firebaseUser = mockk<FirebaseUser>(relaxed = true)

        every { firebaseUser.uid } returns "123456"
        every { firebaseUser.email } returns "test@test.com"
        every { firebaseUser.displayName } returns null

        val userData = firebaseUser.toUserData()

        assertEquals("123456", userData.uid, "El uid debería ser 123456")
        assertEquals("test@test.com", userData.email, "El email debería ser test@test.com")
        assertEquals("", userData.displayName, "El displayName debería ser una cadena vacía")

    }

    @Test
    fun `toUserData debería transformar email null a cadena vacía`() {

        val firebaseUser = mockk<FirebaseUser>(relaxed = true)

        every { firebaseUser.uid } returns "123456"
        every { firebaseUser.email } returns null
        every { firebaseUser.displayName } returns "Test User"

        val userData = firebaseUser.toUserData()

        assertEquals("123456", userData.uid, "El uid debería ser 123456")
        assertEquals("", userData.email, "El email debería ser una cadena vacía")
        assertEquals("Test User", userData.displayName, "El displayName debería ser Test User")

    }

    @Test
    fun `toUserData debería convertir displayName y email null a cadena vacía`() {

        val firebaseUser = mockk<FirebaseUser>(relaxed = true)

        every { firebaseUser.uid } returns "123456"
        every { firebaseUser.email } returns null
        every { firebaseUser.displayName } returns null

        val userData = firebaseUser.toUserData()

        assertEquals("123456", userData.uid, "El uid debería ser 123456")
        assertEquals("", userData.email, "El email debería ser una cadena vacía")
        assertEquals("", userData.displayName, "El displayName debería ser una cadena vacía")
    }
}