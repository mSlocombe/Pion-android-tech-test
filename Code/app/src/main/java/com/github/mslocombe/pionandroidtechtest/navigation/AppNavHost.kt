package com.github.mslocombe.pionandroidtechtest.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.mslocombe.pionandroidtechtest.ui.screen.login.LoginScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Login
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

            Box(
                Modifier
                    .testTag("PhotosScreen")
                    .fillMaxSize()
                    .background(Color.Blue)
            )
        }
    }
}