package com.example.pushmessagetestapp.presentation


import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract

@Composable
fun MainScreen(viewModel: LocalMainScreenViewModel = viewModel()) {

    val state = viewModel.state

    if (state.isLogin) ChatList()
    else SignIn { name -> viewModel.register(name) }
}


