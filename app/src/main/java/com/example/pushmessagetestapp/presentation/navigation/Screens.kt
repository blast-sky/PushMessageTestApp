package com.example.pushmessagetestapp.presentation.navigation

sealed class Screens(val route: String) {

    object Login: Screens("login")

    object Main: Screens("main")

    object Chat: Screens("chat")
}
