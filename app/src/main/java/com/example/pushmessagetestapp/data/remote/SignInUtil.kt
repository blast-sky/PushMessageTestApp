package com.example.pushmessagetestapp.data.remote

import com.example.pushmessagetestapp.R
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

@ViewModelScoped
class SignInUtil @Inject constructor(
    private val fireStoreUtil: StoreUtil
) {

    val signIntent = AuthUI
        .getInstance()
        .createSignInIntentBuilder()
        .setTheme(R.style.Theme_PushMessageTestApp)
        .setAvailableProviders(providers)
        .build()

    fun isSignIn() = FirebaseAuth.getInstance().currentUser != null

    private companion object {
        val providers = listOf(
            AuthUI.IdpConfig.AnonymousBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build(),
        )
    }
}
