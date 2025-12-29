package com.jmarser.vehiclemanager.presentation.navigation

import androidx.navigation.NavHostController
import com.jmarser.vehiclemanager.presentation.vehicles.ui.navigation.VehiclesNavGraph

/**
 * Project: Vehicle manager
 * File: NavigateExtension
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 29/12/2025
 */
 

fun NavHostController.navigateToHome(){
    this.navigate(VehiclesNavGraph.Dest.Root){
        popUpTo(0)
        launchSingleTop = true
    }
}