package com.jmarser.vehiclemanager.data.mapper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Project: Vehicle manager
 * File: FlowResultMapper
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/12/2025
 */

inline fun <T, R> Flow<Result<T>>.mapResult(crossinline mapper: (T) -> R): Flow<Result<R>> =
    this.map { result ->
        result.map {
            mapper(it)
        }
    }
