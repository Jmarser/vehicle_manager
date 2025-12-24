package com.jmarser.vehiclemanager.presentation.auth.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jmarser.vehiclemanager.presentation.auth.ui.screens.ForgotPasswordScreen
import com.jmarser.vehiclemanager.presentation.auth.ui.screens.LoginScreen
import com.jmarser.vehiclemanager.presentation.auth.ui.screens.RegisterScreen
import com.jmarser.vehiclemanager.presentation.auth.ui.screens.SplashScreen
import com.jmarser.vehiclemanager.presentation.navigation.BaseNavGraph
import kotlinx.serialization.Serializable

object AuthNavGraph : BaseNavGraph {
    sealed interface Dest {
        @Serializable
        data object Root : Dest

        @Serializable
        data object Splash : Dest

        @Serializable
        data object Login : Dest

        @Serializable
        data object Register : Dest

        @Serializable
        data object ForgotPassword : Dest
    }

    override fun build(
        modifier: Modifier,
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
    ) {
        navGraphBuilder.navigation<Dest.Root>(
            startDestination = Dest.Splash,
        ) {
            composable<Dest.Splash> {
                SplashScreen(
                    modifier = modifier,
                    navigateToHome = {},
                    navigateToLogin = {
                        navController.navigate(Dest.Login) {
                            popUpTo(Dest.Splash) {
                                inclusive = true
                            }
                        }
                    },
                )
            }

            composable<Dest.Login> {
                LoginScreen(
                    modifier = modifier,
                    navigateToRegister = {
                        navController.navigate(Dest.Register)
                    },
                    navigateToForgotPassword = {
                        navController.navigate(Dest.ForgotPassword)
                    },
                    navigateToHome = {},
                )
            }

            composable<Dest.Register> {
                RegisterScreen(
                    modifier = modifier,
                    navigateToLogin = {
                        navController.popBackStack()
                    },
                    navigateToHome = {},
                )
            }

            composable<Dest.ForgotPassword> {
                ForgotPasswordScreen(
                    modifier = modifier,
                    navigateToLogin = {
                        navController.popBackStack()
                    },
                )
            }
        }
    }
}
