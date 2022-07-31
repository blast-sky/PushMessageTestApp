package com.example.pushmessagetestapp.data.remote

import com.example.pushmessagetestapp.R
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInUtil @Inject constructor(private val fireStoreUtil: StoreUtil) {

    val signIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setTheme(R.style.Theme_PushMessageTestApp)
        .setAvailableProviders(providers)
        .build()

    fun isSignIn() = FirebaseAuth.getInstance().currentUser != null

    companion object {
        private val providers = listOf(
            AuthUI.IdpConfig.AnonymousBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build(),
        )
    }
}
