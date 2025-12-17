package com.jmarser.vehiclemanager.data.mappers

import com.jmarser.vehiclemanager.data.mapper.toDomain
import com.jmarser.vehiclemanager.data.model.UserData
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Project: Vehicle manager
 * File: UserToDomainTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 17/12/2025
 */

class UserToDomainTest {

    @Test
    fun `toDomain debería convertir UserData a User correctamente`() {
        val userData = UserData(
            uid = "12345",
            email = "test@test.com",
            displayName = "Test"
        )

        val domain = userData.toDomain()

        assertEquals(userData.uid, domain.id, "El uid debería ser el mismo")
        assertEquals(userData.email, domain.email, "El email debería ser el mismo")
        assertEquals(userData.displayName, domain.name, "El nombre debería ser el mismo")
    }

    @Test
    fun `toDomain debería transformar email nulo en un string vacío`() {
        val userData = UserData(
            uid = "12345",
            email = null,
            displayName = "Test"
        )

        val domain = userData.toDomain()

        assertEquals(userData.uid, domain.id, "El uid debería ser el mismo")
        assertEquals("", domain.email, "El email debería ser un string vacío")
        assertEquals(userData.displayName, domain.name, "El nombre debería ser el mismo")

    }

    @Test
    fun `toDomain debería transformar displayName nulo en un string vacío`() {
        val userData = UserData(
            uid = "12345",
            email = "test@test.com",
            displayName = null
        )

        val domain = userData.toDomain()

        assertEquals(userData.uid, domain.id, "El uid debería ser el mismo")
        assertEquals(userData.email, domain.email, "El email debería ser el mismo")
        assertEquals("", domain.name, "El nombre debería ser un string vacío")
    }

    @Test
    fun `toDomain debería transformar ambos valores nulos en strings vacíos`() {
        val userData = UserData(
            uid = "12345",
            email = null,
            displayName = null
        )

        val domain = userData.toDomain()

        assertEquals(userData.uid, domain.id, "El uid debería ser el mismo")
        assertEquals("", domain.email, "El email debería ser un string vacío")
        assertEquals("", domain.name, "El nombre debería ser un string vacío")
    }
}