package com.jmarser.vehiclemanager.ui.theme

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Project: Vehicle manager
 * File: Dimens
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 21/11/2025
 */

data class Dimens(
    // -- Bordes --
    val borderExtraSmall: Dp = 1.dp,
    val borderSmall: Dp = 2.dp,
    val borderNormal: Dp = 3.dp,
    // -- Botones --
    val buttonHeightNormal: Dp = 56.dp,
    // -- Icon sizes --
    val iconSizeExtraSmall: Dp = 16.dp,
    val iconSizeSmall: Dp = 24.dp, // Estándar
    val iconSizeNormal: Dp = 32.dp,
    val iconSizeLarge: Dp = 40.dp,
    val iconSizeExtralarge: Dp = 56.dp,
    // -- Paddings (Rellenos internos) --
    val paddingTiny: Dp = 4.dp,
    val paddingSmall: Dp = 8.dp,
    val paddingNormal: Dp = 12.dp,
    val paddingMedium: Dp = 16.dp, // El estándar de la industria
    val paddingLarge: Dp = 20.dp,
    val paddingXL: Dp = 28.dp,
    // -- Spacers (Espaciado entre elementos externos) --
    val spacerSmall: Dp = 4.dp,
    val spacerNormal: Dp = 8.dp,
    val spacerMedium: Dp = 16.dp,
    val spacerLarge: Dp = 24.dp,
    val spacerXXL: Dp = 32.dp,
    // -- Shapes específicos (Radios de esquina) --
    val roundedShapePercent50: CornerSize = CornerSize(50),
    val roundedShapePercent25: CornerSize = CornerSize(25),
    // -- Elevations (Sombras/Profundidad) --
    val cardElevationSmall: Dp = 1.dp,
    val cardElevationNormal: Dp = 3.dp,
    val cardElevationLarge: Dp = 6.dp,
    // -- Otros --
    val logoSize: Dp = 250.dp,
)

val CompactDimens = Dimens()

val MediumDimens =
    Dimens(
        borderExtraSmall = 2.dp,
        borderSmall = 3.dp,
        borderNormal = 5.dp,
        buttonHeightNormal = 64.dp, // Botones más fáciles de tocar
        iconSizeExtraSmall = 16.dp,
        iconSizeSmall = 24.dp,
        iconSizeNormal = 40.dp,
        iconSizeLarge = 48.dp,
        iconSizeExtralarge = 64.dp,
        paddingTiny = 8.dp,
        paddingSmall = 12.dp,
        paddingNormal = 16.dp,
        paddingMedium = 20.dp, // Más aire en los contenedores
        paddingLarge = 28.dp,
        paddingXL = 36.dp,
        spacerSmall = 8.dp,
        spacerNormal = 12.dp,
        spacerMedium = 20.dp,
        spacerLarge = 28.dp,
        spacerXXL = 36.dp,
        cardElevationSmall = 3.dp, // Sombras ligeramente más pronunciadas
        cardElevationNormal = 6.dp,
        cardElevationLarge = 8.dp,
        logoSize = 300.dp,
    )

val ExpandedDimens =
    Dimens(
        borderExtraSmall = 3.dp,
        borderSmall = 4.dp,
        borderNormal = 6.dp,
        buttonHeightNormal = 72.dp,
        iconSizeExtraSmall = 24.dp,
        iconSizeSmall = 32.dp,
        iconSizeNormal = 48.dp,
        iconSizeLarge = 56.dp,
        iconSizeExtralarge = 72.dp,
        paddingTiny = 12.dp,
        paddingSmall = 16.dp,
        paddingNormal = 24.dp,
        paddingMedium = 32.dp, // Mucho espacio blanco, estilo editorial
        paddingLarge = 40.dp,
        paddingXL = 48.dp,
        spacerSmall = 16.dp,
        spacerNormal = 24.dp,
        spacerMedium = 32.dp,
        spacerLarge = 40.dp,
        spacerXXL = 48.dp,
        cardElevationSmall = 6.dp,
        cardElevationNormal = 8.dp,
        cardElevationLarge = 12.dp,
        logoSize = 420.dp,
    )
