package com.jmarser.vehiclemanager.core.presentation.ui

import androidx.compose.material3.Typography
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import com.jmarser.vehiclemanager.ui.theme.CompactTypography
import com.jmarser.vehiclemanager.ui.theme.ExpandedTypography
import com.jmarser.vehiclemanager.ui.theme.MediumTypography

/**
 * Project: Vehicle manager
 * File: DeviceUtils
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 21/11/2025
 */
 
@Composable
fun isTablet(): Boolean{
    val configuration = LocalConfiguration.current

    return remember(configuration){
        val smallestWidth = minOf(configuration.screenWidthDp, configuration.screenHeightDp)
        smallestWidth >= 600
    }
}

@Composable
fun isTabletLandscape(): Boolean{
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    val isLandscape = screenWidthDp > screenHeightDp
    val isTablet = isTablet()

    return isLandscape && isTablet
}

fun typographyForWindowSize(
    windowSizeClass: WindowSizeClass,
    isTablet: Boolean
): Typography{
    return if (isTablet){
        when(windowSizeClass.widthSizeClass){
            WindowWidthSizeClass.Compact -> CompactTypography
            WindowWidthSizeClass.Medium -> MediumTypography
            WindowWidthSizeClass.Expanded -> ExpandedTypography
            else -> CompactTypography
        }
    }else{
        CompactTypography
    }
}

val localAppTypography = staticCompositionLocalOf { CompactTypography }
val appTypography: Typography @Composable get() = localAppTypography.current