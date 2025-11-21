package com.jmarser.vehiclemanager.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Project: Vehicle manager
 * File: Shapes
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 21/11/2025
 */
 
val CompactShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp), // tooltips
    small = RoundedCornerShape(8.dp), // Botones pequeños, campos de texto
    medium = RoundedCornerShape(16.dp), //Card, Diálogo
    large = RoundedCornerShape(24.dp), // Navigation Drawer
    extraLarge = RoundedCornerShape(32.dp) // Botones de accion flotante
)

val MediumShapes = Shapes(
    extraSmall = RoundedCornerShape(6.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(18.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(30.dp)
)

val ExpandedShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(16.dp),
    medium = RoundedCornerShape(24.dp),
    large = RoundedCornerShape(32.dp),
    extraLarge = RoundedCornerShape(48.dp)
)