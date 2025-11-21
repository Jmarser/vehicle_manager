package com.jmarser.vehiclemanager.core.presentation.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jmarser.vehiclemanager.core.presentation.ui.appDimens

/**
 * Project: Vehicle manager
 * File: AppSpacers
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 21/11/2025
 */

/**
 * -------------------------------------------------------------
 * ESPACIADORES VERTICALES (Añaden Altura / Height)
 * Útiles dentro de Columnas (Column) para separar elementos de arriba a abajo.
 * -------------------------------------------------------------
 */

/** Espacio vertical pequeño. (C:4dp, M:8dp, E:16dp) */
@Composable
fun VerticalSpaceSmall() {
    Spacer(modifier = Modifier.height(appDimens.spacerSmall))
}

/** Espacio vertical normal. (C:8dp, M:12dp, E:24dp) */
@Composable
fun VerticalSpaceNormal() {
    Spacer(modifier = Modifier.height(appDimens.spacerNormal))
}

/** Espacio vertical medio. El estándar más común. (C:16dp, M:20dp, E:32dp) */
@Composable
fun VerticalSpaceMedium() {
    Spacer(modifier = Modifier.height(appDimens.spacerMedium))
}

/** Espacio vertical grande. Para separar secciones. (C:24dp, M:28dp, E:40dp) */
@Composable
fun VerticalSpaceLarge() {
    Spacer(modifier = Modifier.height(appDimens.spacerLarge))
}

/** Espacio vertical extra grande. (C:32dp, M:36dp, E:48dp) */
@Composable
fun VerticalSpaceExtraLarge() {
    Spacer(modifier = Modifier.height(appDimens.spacerXXL))
}


/**
 * -------------------------------------------------------------
 * ESPACIADORES HORIZONTALES (Añaden Anchura / Width)
 * Útiles dentro de Filas (Row) para separar elementos de lado a lado.
 * -------------------------------------------------------------
 */

/** Espacio horizontal pequeño. (C:4dp, M:8dp, E:16dp) */
@Composable
fun HorizontalSpaceSmall() {
    Spacer(modifier = Modifier.width(appDimens.spacerSmall))
}

/** Espacio horizontal normal. (C:8dp, M:12dp, E:24dp) */
@Composable
fun HorizontalSpaceNormal() {
    Spacer(modifier = Modifier.width(appDimens.spacerNormal))
}

/** Espacio horizontal medio. (C:16dp, M:20dp, E:32dp) */
@Composable
fun HorizontalSpaceMedium() {
    Spacer(modifier = Modifier.width(appDimens.spacerMedium))
}

/** Espacio horizontal grande. (C:24dp, M:28dp, E:40dp) */
@Composable
fun HorizontalSpaceLarge() {
    Spacer(modifier = Modifier.width(appDimens.spacerLarge))
}

/** Espacio horizontal extra grande. (C:32dp, M:36dp, E:48dp) */
@Composable
fun HorizontalSpaceExtraLarge() {
    Spacer(modifier = Modifier.width(appDimens.spacerXXL))
}