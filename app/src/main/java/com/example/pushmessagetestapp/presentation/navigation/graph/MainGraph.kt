package com.example.pushmessagetestapp.presentation.navigation.graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.astrog.chats_list.presentation.chat_screen.compose.ChatScreen
import com.astrog.login.presentation.LoginScreen
import com.astrog.chats_list.presentation.chat_list_screen.ChatListScreenViewModel
import com.astrog.chats_list.presentation.chat_list_screen.compose.ChatListScreen
import com.astrog.chats_list.presentation.chat_screen.ChatScreenViewModel
import com.example.pushmessagetestapp.presentation.navigation.Screens


fun NavGraphBuilder.mainGraph(navController: NavController, routeName: String) =
    navigation(Screens.Login.route, routeName) {
        composable(route = Screens.Main.route) {
            val viewModel = hiltViewModel<ChatListScreenViewModel>()
            ChatListScreen(
                goToLoginScreen = { navController.navigate(Screens.Login.route) {
                    popUpTo(Screens.Main.route) { inclusive = true }
                } },
                onChatClicked = { chatId, chatTitle ->
                    navController.navigate("${Screens.Chat.route}/$chatId/$chatTitle")
                },
            )
        }

        val chatIdArgName = "chatId"
        val chatTitleArgName = "chatTitle"
        composable(
            route = Screens.Chat.route + "/{$chatIdArgName}/{$chatTitleArgName}",
            arguments = listOf(
                navArgument(chatIdArgName) { type = NavType.StringType },
                navArgument(chatTitleArgName) { type = NavType.StringType }
            )
        ) {
            ChatScreen(
                chatId = it.arguments!!.getString(chatIdArgName)!!,
                chatTitle = it.arguments!!.getString(chatTitleArgName)!!,
                goBack = navController::popBackStack
            )
        }

        composable(route = Screens.Login.route) {
            LoginScreen {
                navController.navigate(Screens.Main.route) {
                    popUpTo(Screens.Login.route) { inclusive = true }
                }
            }
        }
    }
