package com.example.pushmessagetestapp.presentation


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(viewModel: LocalMainScreenViewModel = viewModel()) {

    val state = viewModel.state

    if (state.isLogin) ChatList(state.chats)
    else SignIn { name -> viewModel.register(name) }
}


