package com.jmarser.vehiclemanager.presentation.navigation


import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jmarser.vehiclemanager.presentation.auth.ui.navigation.AuthNavGraph

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val ANIMATION_DURATION = 700
    val SCALE: Float = .7f

    NavHost(
        navController = navController,
        startDestination = AuthNavGraph.Dest.Root,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(ANIMATION_DURATION)
            ) + fadeIn(animationSpec = tween(ANIMATION_DURATION)
            ) + scaleIn(
                initialScale = SCALE,
                animationSpec = tween(ANIMATION_DURATION)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(ANIMATION_DURATION)
            ) + fadeOut(animationSpec = tween(ANIMATION_DURATION)
            ) + scaleOut(
                targetScale = SCALE,
                animationSpec = tween(ANIMATION_DURATION)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(ANIMATION_DURATION)
            ) + fadeIn(animationSpec = tween(ANIMATION_DURATION)
            ) + scaleIn(
                initialScale = SCALE,
                animationSpec = tween(ANIMATION_DURATION)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(ANIMATION_DURATION)
            ) + fadeOut(animationSpec = tween(ANIMATION_DURATION)
            ) + scaleOut(
                targetScale = SCALE,
                animationSpec = tween(ANIMATION_DURATION)
            )
        }

    ) {
        listOf<BaseNavGraph>(
            AuthNavGraph
        ).forEach {
            it.build(
                modifier = modifier,
                navController = navController,
                navGraphBuilder = this
            )
        }
    }

}


