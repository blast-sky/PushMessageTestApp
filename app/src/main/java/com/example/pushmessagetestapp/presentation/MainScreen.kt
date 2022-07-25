package com.example.pushmessagetestapp.presentation


import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract

@Composable
fun MainScreen(viewModel: MainScreenViewModel = viewModel()) {

    val isSighIn by viewModel.isSignInState

    val launcher = rememberLauncherForActivityResult(
        contract = FirebaseAuthUIActivityResultContract(),
        onResult = viewModel::onSignInResult
    )

    if (isSighIn) ChatList()
    else SignIn { launcher.launch(viewModel.signInIntent) }

}


