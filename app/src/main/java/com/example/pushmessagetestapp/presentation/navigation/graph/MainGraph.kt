package com.example.pushmessagetestapp.presentation.navigation.graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.pushmessagetestapp.presentation.chat_screen.ChatScreenViewModel
import com.example.pushmessagetestapp.presentation.chat_screen.compose.ChatScreen
import com.example.pushmessagetestapp.presentation.login_screen.LoginScreen
import com.example.pushmessagetestapp.presentation.login_screen.LoginScreenViewModel
import com.example.pushmessagetestapp.presentation.chat_list_screen.ChatListScreenViewModel
import com.example.pushmessagetestapp.presentation.chat_list_screen.compose.ChatListScreen
import com.example.pushmessagetestapp.presentation.navigation.Screens


fun NavGraphBuilder.mainGraph(navController: NavController, routeName: String) =
    navigation(Screens.Login.route, routeName) {
        composable(route = Screens.Main.route) {
            val viewModel = hiltViewModel<ChatListScreenViewModel>()
            ChatListScreen(
                chats = viewModel.chats,
                availableUsers = viewModel.availableUsers,
                onChatClicked = { chatId -> navController.navigate(Screens.Chat.route + "/$chatId") },
                createChat = viewModel::createChat,
                loadAvailableUsers = viewModel::loadAvailableUsers
            )
        }

        val chatIdArgName = "chatId"
        composable(
            route = Screens.Chat.route + "/{$chatIdArgName}",
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

        composable(route = Screens.Login.route) {
            val viewModel = hiltViewModel<LoginScreenViewModel>()
            LoginScreen(isLogin = viewModel.isLogin, onLoginClicked = viewModel::register) {
                navController.navigate(Screens.Main.route) {
                    popUpTo(Screens.Login.route) { inclusive = true }
                }
            }
        }
    }
