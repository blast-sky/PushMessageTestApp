package com.example.pushmessagetestapp.presentation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pushmessagetestapp.data.SharedPreferencesUserUtil
import com.example.pushmessagetestapp.data.SignInUtil
import com.example.pushmessagetestapp.data.StoreUtil
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val signInUtil: SignInUtil,
    private val storeUtil: StoreUtil,
    private val sharedPreferencesUserUtil: SharedPreferencesUserUtil
) : ViewModel() {

    val signInIntent = signInUtil.signIntent

    val isSignInState = mutableStateOf(signInUtil.isSignIn())

    fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        when (result.resultCode) {
            Activity.RESULT_OK -> isSignInState.value = true
            Activity.RESULT_CANCELED -> isSignInState.value = false
        }
    }
}