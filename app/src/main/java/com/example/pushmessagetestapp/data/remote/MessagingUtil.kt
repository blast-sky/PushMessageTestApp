package com.example.pushmessagetestapp.data.remote

import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MessagingUtil @Inject constructor() {

    suspend fun getToken(): String = FirebaseMessaging.getInstance().token.suspend()
}