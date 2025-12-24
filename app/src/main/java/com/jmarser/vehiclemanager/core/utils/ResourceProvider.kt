package com.jmarser.vehiclemanager.core.utils

import androidx.annotation.StringRes

/**
 * Project: Vehicle manager
 * File: ResourceProvider
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/12/2025
 */

interface ResourceProvider {
    fun getString(
        @StringRes id: Int,
    ): String

    fun getStringWithArgs(
        @StringRes id: Int,
        vararg args: Any,
    ): String
}
