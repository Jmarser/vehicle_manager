package com.jmarser.vehiclemanager.core.presentation.ui

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/**
 * Project: Vehicle manager
 * File: WindowSizeClassUtils
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 21/11/2025
 */

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun getSizeForPhone(): WindowSizeClass = WindowSizeClass.calculateFromSize(DpSize(360.dp, 800.dp))

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun getSizeForTablet(): WindowSizeClass = WindowSizeClass.calculateFromSize(DpSize(800.dp, 1280.dp))

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun getSizeForDesktop(): WindowSizeClass = WindowSizeClass.calculateFromSize(DpSize(1280.dp, 900.dp))
