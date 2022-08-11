package com.example.pushmessagetestapp.data.remote

import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessagingUtil @Inject constructor() {

    suspend fun getToken(): String = FirebaseMessaging.getInstance().token.suspend()
}