package com.example.pushmessagetestapp.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.pushmessagetestapp.presentation.chat_screen.ChatScreenViewModel
import com.example.pushmessagetestapp.presentation.chat_screen.compose.ChatScreen
import com.example.pushmessagetestapp.presentation.main_screen.MainScreenViewModel
import com.example.pushmessagetestapp.presentation.main_screen.compose.MainScreen


fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(Screen.Main.route, "mainGraph") {
        composable(route = Screen.Main.route) {
            val viewModel = hiltViewModel<MainScreenViewModel>()
            MainScreen(
                state = viewModel.state,
                registerNewUser = viewModel::register,
                onChatClicked = { chatId -> navController.navigate(Screen.Chat.route + "/$chatId") },
            )
        }

        val chatIdArgName = "chatId"
        composable(
            route = Screen.Chat.route + "/{$chatIdArgName}",
            arguments = listOf(navArgument(chatIdArgName) { type = NavType.StringType })
        ) {
            val viewModel = hiltViewModel<ChatScreenViewModel>()
            ChatScreen(
                chatId = it.arguments?.getString(chatIdArgName)!!,
                myUserId = viewModel.userId,
                messages = viewModel.messages,
                loadMessage = viewModel::loadMessages,
                sendMessage = viewModel::sendMessage,
            )
        }
    }
}