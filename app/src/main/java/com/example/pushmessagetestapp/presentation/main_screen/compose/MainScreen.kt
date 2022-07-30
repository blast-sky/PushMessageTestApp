package com.example.pushmessagetestapp.presentation.main_screen.compose


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pushmessagetestapp.presentation.main_screen.LocalMainScreenViewModel

@Composable
fun MainScreen(
    viewModel: LocalMainScreenViewModel = hiltViewModel(),
    onChatClicked: (String) -> Unit,
) {
    val state = viewModel.state

    if (state.isLogin) ChatList(state.chats, onChatClicked)
    else SignIn { name -> viewModel.register(name) }
}


