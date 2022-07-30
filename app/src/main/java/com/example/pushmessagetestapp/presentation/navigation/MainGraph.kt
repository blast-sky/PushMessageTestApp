package com.example.pushmessagetestapp.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.pushmessagetestapp.presentation.chat_screen.ChatScreen
import com.example.pushmessagetestapp.presentation.main_screen.compose.MainScreen


fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(Screen.Main.route, "mainGraph") {
        composable(route = Screen.Main.route) {
            MainScreen { chatId -> navController.navigate(Screen.Chat.route + "/$chatId") }
        }

        val chatIdArgName = "chatId"
        composable(
            route = Screen.Chat.route + "/{$chatIdArgName}",
            arguments = listOf(navArgument(chatIdArgName) { type = NavType.StringType })
        ) {
            ChatScreen(chatId = it.arguments?.getString(chatIdArgName)!!)
        }
    }
}