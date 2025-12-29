package com.jmarser.vehiclemanager.presentation.vehicles.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jmarser.vehiclemanager.presentation.navigation.BaseNavGraph
import com.jmarser.vehiclemanager.presentation.vehicles.ui.screens.ListVehiclesScreen
import kotlinx.serialization.Serializable

object VehiclesNavGraph: BaseNavGraph {

    sealed interface Dest{
        @Serializable
        data object Root: Dest
        @Serializable
        data object ListVehicles: Dest
        @Serializable
        data object AddVehicle: Dest
        @Serializable
        data object DetailsVehicle: Dest
    }

    override fun build(
        modifier: Modifier,
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<Dest.Root>(
            startDestination = Dest.ListVehicles
        ){
            composable<Dest.ListVehicles>{
                ListVehiclesScreen(modifier = modifier)
            }
            composable<Dest.DetailsVehicle>{

            }
            composable<Dest.AddVehicle>{

            }
        }
    }
}