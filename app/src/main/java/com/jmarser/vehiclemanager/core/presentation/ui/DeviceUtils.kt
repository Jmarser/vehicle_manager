package com.jmarser.vehiclemanager.core.presentation.ui

import android.content.res.Configuration
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import com.jmarser.vehiclemanager.ui.theme.CompactDimens
import com.jmarser.vehiclemanager.ui.theme.CompactShapes
import com.jmarser.vehiclemanager.ui.theme.CompactTypography
import com.jmarser.vehiclemanager.ui.theme.Dimens
import com.jmarser.vehiclemanager.ui.theme.ExpandedDimens
import com.jmarser.vehiclemanager.ui.theme.ExpandedShapes
import com.jmarser.vehiclemanager.ui.theme.ExpandedTypography
import com.jmarser.vehiclemanager.ui.theme.MediumDimens
import com.jmarser.vehiclemanager.ui.theme.MediumShapes
import com.jmarser.vehiclemanager.ui.theme.MediumTypography

/**
 * Project: Vehicle manager
 * File: DeviceUtils
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 21/11/2025
 */

@Composable
fun isTablet(): Boolean {
    val configuration = LocalConfiguration.current

    return remember(configuration) {
        val smallestWidth = minOf(configuration.screenWidthDp, configuration.screenHeightDp)
        smallestWidth >= 600
    }
}

sealed class DeviceOrientation {
    object Portrait : DeviceOrientation()

    object Landscape : DeviceOrientation()

    object Undefined : DeviceOrientation()
}

@Composable
fun rememberDeviceOrientation(): DeviceOrientation {
    val configuration = LocalConfiguration.current

    return when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> DeviceOrientation.Portrait
        Configuration.ORIENTATION_LANDSCAPE -> DeviceOrientation.Landscape
        else -> DeviceOrientation.Undefined
    }
}

@Composable
fun isTabletLandscape(): Boolean {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    val isLandscape = screenWidthDp > screenHeightDp
    val isTablet = isTablet()

    return isLandscape && isTablet
}

fun typographyForWindowSize(
    windowSizeClass: WindowSizeClass,
    isTablet: Boolean,
): Typography =
    if (isTablet) {
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> CompactTypography
            WindowWidthSizeClass.Medium -> MediumTypography
            WindowWidthSizeClass.Expanded -> ExpandedTypography
            else -> CompactTypography
        }
    } else {
        CompactTypography
    }

val localAppTypography = staticCompositionLocalOf { CompactTypography }
val appTypography: Typography @Composable get() = localAppTypography.current

fun shapesForWindowSize(
    windowSizeClass: WindowSizeClass,
    isTablet: Boolean,
): Shapes =
    if (isTablet) {
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> CompactShapes
            WindowWidthSizeClass.Medium -> MediumShapes
            WindowWidthSizeClass.Expanded -> ExpandedShapes
            else -> CompactShapes
        }
    } else {
        CompactShapes
    }

val localAppShapes = staticCompositionLocalOf { CompactShapes }
val appShapes: Shapes @Composable get() = localAppShapes.current

fun dimensForWindowSize(
    windowSizeClass: WindowSizeClass,
    isTablet: Boolean,
): Dimens =
    if (isTablet) {
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> CompactDimens
            WindowWidthSizeClass.Medium -> MediumDimens
            WindowWidthSizeClass.Expanded -> ExpandedDimens
            else -> CompactDimens
        }
    } else {
        CompactDimens
    }

val localDimens = staticCompositionLocalOf { CompactDimens }
val appDimens: Dimens @Composable get() = localDimens.current
