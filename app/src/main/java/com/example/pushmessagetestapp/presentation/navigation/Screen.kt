package com.example.pushmessagetestapp.presentation.navigation

sealed class Screen(val route: String) {

    object Main: Screen("main")

    object Chat: Screen("chat")

}
