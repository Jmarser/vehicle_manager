package com.jmarser.vehiclemanager.data.mappers

import com.jmarser.vehiclemanager.data.mapper.mapResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Project: Vehicle manager
 * File: FlowResultMapperTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 17/12/2025
 */

class FlowResultMapperTest {

    @Test
    fun `mapResult debería transformar el contenido axitoso de un Flow`() = runTest {

        val flow = flowOf(Result.success("10"))

        val mappedFlow = flow.mapResult { it.toInt() }
        val result = mappedFlow.first()

        assertEquals(10, result.getOrNull())
        assertTrue { result.isSuccess }
    }

    @Test
    fun `mapResult no debería alterar el resultado si es un fallo`() = runTest {

        val exception = Exception("Error de prueba")
        val flow = flowOf(Result.failure<String>(exception))

        val mappedFlow = flow.mapResult { it.toInt() }
        val result = mappedFlow.first()

        assertTrue { result.isFailure }
        assertEquals(exception, result.exceptionOrNull())
    }
}