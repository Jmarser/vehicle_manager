package com.jmarser.vehiclemanager.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.jmarser.vehiclemanager.R

object AppImages {

    @Composable
    fun logo(): Painter = painterResource(R.drawable.header_img)

    val ic_email: ImageVector @Composable get() = Icons.Default.Email
}