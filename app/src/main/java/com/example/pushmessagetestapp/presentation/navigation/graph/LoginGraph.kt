package com.example.pushmessagetestapp.presentation.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.astrog.login.presentation.LoginScreen
import com.example.pushmessagetestapp.presentation.navigation.Screens

// Graphs don't work in that version of navigation
fun NavGraphBuilder.loginGraph(
    navController: NavController,
    routeName: String,
    nextRouteName: String
) = navigation(startDestination = Screens.Login.route, route = routeName) {
    composable(route = Screens.Login.route) {
        LoginScreen {
            navController.navigate(nextRouteName) {
                popUpTo(Screens.Login.route) { inclusive = true }
            }
        }
    }
}
