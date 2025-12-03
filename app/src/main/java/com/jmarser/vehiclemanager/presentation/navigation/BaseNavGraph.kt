package com.jmarser.vehiclemanager.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * Project: Vehicle manager
 * File: BaseNavGraph
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 28/11/2025
 */

interface BaseNavGraph {
    fun build(
        modifier: Modifier,
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
    )
}
