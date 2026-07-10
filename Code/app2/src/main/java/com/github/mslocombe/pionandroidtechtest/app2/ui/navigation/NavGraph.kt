package com.github.mslocombe.pionandroidtechtest.app2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.mslocombe.pionandroidtechtest.app2.ui.login.LoginScreen
import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = LoginRoute
    ) {
        composable<LoginRoute> {
            LoginScreen()
        }
    }
}
