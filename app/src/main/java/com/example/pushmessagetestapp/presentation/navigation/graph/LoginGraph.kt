package com.example.pushmessagetestapp.presentation.navigation.graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.pushmessagetestapp.presentation.login_screen.LoginScreen
import com.example.pushmessagetestapp.presentation.login_screen.LoginScreenViewModel
import com.example.pushmessagetestapp.presentation.navigation.Screens


fun NavGraphBuilder.loginGraph(
    navController: NavController,
    routeName: String,
    nextRouteName: String
) = navigation(startDestination = Screens.Login.route, route = routeName) {
    composable(route = Screens.Login.route) {
        val viewModel = hiltViewModel<LoginScreenViewModel>()
        LoginScreen(isLogin = viewModel.isLogin, onLoginClicked = viewModel::register) {
            navController.navigate(nextRouteName) {
                //popUpTo(Screens.Login.route) { inclusive = true }
            }
        }
    }
}
