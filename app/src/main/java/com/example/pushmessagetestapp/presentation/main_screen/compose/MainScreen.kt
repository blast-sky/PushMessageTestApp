package com.example.pushmessagetestapp.presentation.main_screen.compose


import androidx.compose.runtime.Composable
import com.example.pushmessagetestapp.presentation.main_screen.MainScreenState

@Composable
fun MainScreen(
    state: MainScreenState,
    onChatClicked: (String) -> Unit,
    registerNewUser: (String) -> Unit,
) {
    if (state.isLogin)
        ChatList(state.chats, onChatClicked)
    else
        SignIn(registerNewUser)
}


