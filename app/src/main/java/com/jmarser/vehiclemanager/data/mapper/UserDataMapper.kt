package com.jmarser.vehiclemanager.data.mapper

import com.jmarser.vehiclemanager.data.model.UserData
import com.jmarser.vehiclemanager.domain.model.User

/**
 * Project: Vehicle manager
 * File: UserDataMapper
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/12/2025
 */

fun UserData.toDomain() : User{
    return User(
        id = this.uid,
        email = this.email.orEmpty(),
        name = this.displayName.orEmpty()
    )
}