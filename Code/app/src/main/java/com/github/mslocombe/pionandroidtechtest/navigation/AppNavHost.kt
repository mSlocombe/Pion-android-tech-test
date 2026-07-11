package com.github.mslocombe.pionandroidtechtest.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.mslocombe.pionandroidtechtest.ui.screen.login.LoginScreen
import com.github.mslocombe.pionandroidtechtest.ui.screen.photos.PhotosScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Login> {
            LoginScreen(
                navigateToPhotos = {
                    navController.navigate(Photos) {
                        popUpTo(navController.graph.id)
                    }
                }
            )
        }

        composable<Photos> {
            BackHandler {
                navController.navigate(Login) {
                    popUpTo(navController.graph.id)
                }
            }

            PhotosScreen(
                onBack = {
                    navController.navigate(Login) {
                        popUpTo(navController.graph.id)
                    }
                }
            )
        }
    }
}