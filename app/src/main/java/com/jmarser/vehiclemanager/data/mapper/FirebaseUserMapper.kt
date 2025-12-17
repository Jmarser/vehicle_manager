package com.jmarser.vehiclemanager.data.mapper

import com.google.firebase.auth.FirebaseUser
import com.jmarser.vehiclemanager.data.model.UserData

/**
 * Project: Vehicle manager
 * File: FirebaseUserMapper
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/12/2025
 */

fun FirebaseUser.toUserData(): UserData =
    UserData(
        uid = this.uid,
        email = this.email ?: "",
        displayName = this.displayName ?: "",
    )
