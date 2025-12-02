package com.jmarser.vehiclemanager.core.utils

import android.content.Context

/**
 * Project: Vehicle manager
 * File: ResourceProviderImpl
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/12/2025
 */

class ResourceProviderImpl(
    private val context: Context
): ResourceProvider {
    override fun getString(id: Int): String {
        return context.getString(id)
    }

    override fun getStringWithArgs(id: Int, vararg args: Any): String {
        return context.getString(id, *args)
    }
}